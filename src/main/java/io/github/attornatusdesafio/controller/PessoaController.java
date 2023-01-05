package io.github.attornatusdesafio.controller;

import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(){
        List<Pessoa> pessoas = service.listAll();
        return ResponseEntity.ok().body(pessoas);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Integer id){
        Pessoa pessoa = service.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }
}
