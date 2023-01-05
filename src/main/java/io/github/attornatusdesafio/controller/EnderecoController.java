package io.github.attornatusdesafio.controller;

import io.github.attornatusdesafio.dto.NewEnderecoDto;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("bypessoas/{pessoaId}")
    public ResponseEntity<List<Endereco>> listAllEnderecoByPessoa(@PathVariable Integer pessoaId){
        List<Endereco> enderecos = service.listAllEnderecoByPessoa(pessoaId);
        return ResponseEntity.ok().body(enderecos);
    }
    @PostMapping("insert/{pessoaId}")
    public ResponseEntity<Void> insertEndereco(@PathVariable Integer pessoaId, @RequestBody NewEnderecoDto dto){
        Endereco endereco = service.insertEndereco(pessoaId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("main/bypessoas/{pessoaId}")
    public ResponseEntity<Endereco> findMainEnderecoByPessoa(@PathVariable Integer pessoaId){
        Endereco mainEndereco = service.findMainEnderecoByPessoa(pessoaId);
        return ResponseEntity.ok().body(mainEndereco);
    }
}
