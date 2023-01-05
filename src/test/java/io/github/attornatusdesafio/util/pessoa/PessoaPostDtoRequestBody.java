package io.github.attornatusdesafio.util.pessoa;

import io.github.attornatusdesafio.dto.NewPessoaDto;

public class PessoaPostDtoRequestBody {

    public static NewPessoaDto createdPessoaPostRequestBody(){
        NewPessoaDto dto = new NewPessoaDto();
        dto.setNome(CreatePessoaTest.pessoaToBeCreated().getNome());
        dto.setDataNascimento(CreatePessoaTest.pessoaToBeCreated().getDataNascimento());
        return dto;
    }
}
