package io.github.attornatusdesafio.util.pessoa;

import io.github.attornatusdesafio.model.Pessoa;

import java.time.LocalDate;

public class CreatePessoaTest {

    public static Pessoa pessoaToBeSaved(){
        return new Pessoa("Guilherme", LocalDate.now());
    }

    public static Pessoa pessoaToBeCreated(){
        return new Pessoa(1, "Guilherme", LocalDate.now());
    }

}
