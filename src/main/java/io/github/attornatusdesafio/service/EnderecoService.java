package io.github.attornatusdesafio.service;

import io.github.attornatusdesafio.dto.NewEnderecoDto;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.repository.EnderecoRepository;
import io.github.attornatusdesafio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Endereco insertEndereco(Integer pessoaId, NewEnderecoDto dto) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow();

        Endereco endereco = new Endereco(dto.getLogradouro(), dto.getCep(),
                dto.getNumero(), dto.getCidade(), List.of(pessoa));
        pessoa.getEnderecos().add(endereco);

        endereco = enderecoRepository.save(endereco);
        pessoaRepository.saveAll(endereco.getPessoas());
        return endereco;
    }

    public List<Endereco> listAllEnderecoByPessoa(Integer pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow();
        return pessoa.getEnderecos();
    }

    public Endereco findMainEnderecoByPessoa(Integer pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow();

        //todo: A regra de negocio é que o primeiro Endereco sempre será o principal endereco da pessoa.
        if (pessoa.getEnderecos().size() == 0) {
            throw new IllegalArgumentException("Esse cliente não possui enderecos!", new IllegalArgumentException());
        } else {
            return pessoa.getEnderecos().get(0);
        }
    }
}
