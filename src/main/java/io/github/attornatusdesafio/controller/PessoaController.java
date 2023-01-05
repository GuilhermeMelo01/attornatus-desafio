package io.github.attornatusdesafio.controller;

import io.github.attornatusdesafio.dto.NewPessoaDto;
import io.github.attornatusdesafio.dto.UpdatePessoaDto;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("insert")
    public ResponseEntity<Void> insert(@RequestBody NewPessoaDto dto){
        Pessoa pessoa = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UpdatePessoaDto dto){
        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }
}
