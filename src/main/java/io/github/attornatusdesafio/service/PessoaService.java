package io.github.attornatusdesafio.service;

import io.github.attornatusdesafio.dto.NewPessoaDto;
import io.github.attornatusdesafio.dto.UpdatePessoaDto;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    //Busca uma pesssoa a partir do seu Id.
    public Pessoa findById(Integer id){
        return pessoaRepository.findById(id).orElseThrow();
    }

    //Lista todas as pessoas com os seus enderecos.
    public List<Pessoa> listAll(){
        return pessoaRepository.findAll();
    }

    //Insere uma nova pessoa a partir do Dto (Data Transfer Object)
    public Pessoa insert(NewPessoaDto dto){
        Pessoa pessoa = new Pessoa(dto.getNome(), dto.getDataNascimento());
        return pessoaRepository.save(pessoa);
    }

    //Faz uma atualização na pessoa com Id passado no paramentro
    public void update(Integer id, UpdatePessoaDto dto){
        Pessoa pessoa = findById(id);
        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoaRepository.save(pessoa);
    }
}
