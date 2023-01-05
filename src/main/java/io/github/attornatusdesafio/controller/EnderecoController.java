package io.github.attornatusdesafio.controller;

import io.github.attornatusdesafio.dto.NewEnderecoDto;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping("/{id}")
    public ResponseEntity<Void> insertEndereco(@PathVariable Integer id, @RequestBody NewEnderecoDto dto){
        Endereco endereco = service.insertEndereco(id, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
