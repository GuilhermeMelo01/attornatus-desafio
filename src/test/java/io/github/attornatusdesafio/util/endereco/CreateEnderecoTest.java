package io.github.attornatusdesafio.util.endereco;

import io.github.attornatusdesafio.enums.TipoEndereco;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.model.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class CreateEnderecoTest {


    public static Endereco enderecoToBeCreated(){
        return new Endereco(1,"Rua celso correia", "61611220",
                "600", "Fortaleza", List.of(new Pessoa()));
    }
}
