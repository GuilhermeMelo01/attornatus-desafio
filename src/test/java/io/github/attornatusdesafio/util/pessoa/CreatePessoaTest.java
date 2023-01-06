package io.github.attornatusdesafio.util.pessoa;

import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.model.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class CreatePessoaTest {

    public static Pessoa pessoaToBeSaved(){
        return new Pessoa("Guilherme", LocalDate.now());
    }

    public static Pessoa pessoaToBeCreated(){
        return new Pessoa(1, "Guilherme", LocalDate.now());
    }

    public static Pessoa pessoaToBeCreatedWithEndereco(){
        Pessoa pessoa = new Pessoa(1, "Guilherme", LocalDate.now());
        Endereco endereco = new Endereco(1,"Rua celso correia", "61611220",
                "600", "Fortaleza", List.of(pessoa));
        pessoa.getEnderecos().add(endereco);
        return pessoa;
    }

}
