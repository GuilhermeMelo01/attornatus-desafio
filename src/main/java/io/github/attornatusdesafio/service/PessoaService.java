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

    public Pessoa findById(Integer id){
        return pessoaRepository.findById(id).orElseThrow();
    }

    public List<Pessoa> listAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa insert(NewPessoaDto dto){
        Pessoa pessoa = new Pessoa(dto.getNome(), dto.getDataNascimento());
        return pessoaRepository.save(pessoa);
    }

    public void update(Integer id, UpdatePessoaDto dto){
        Pessoa pessoa = findById(id);
        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoaRepository.save(pessoa);
    }

}
