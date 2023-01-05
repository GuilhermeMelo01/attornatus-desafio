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
    public Endereco insertEndereco(Integer idPessoa, NewEnderecoDto dto){
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow();

        Endereco endereco = new Endereco(dto.getLogradouro(), dto.getCep(),
                dto.getNumero(), dto.getCidade(), List.of(pessoa));
        pessoa.getEnderecos().add(endereco);

        endereco = enderecoRepository.save(endereco);
        pessoaRepository.saveAll(endereco.getPessoas());
        return endereco;
    }
}
