package io.github.attornatusdesafio.util.pessoa;

import io.github.attornatusdesafio.dto.NewPessoaDto;
import io.github.attornatusdesafio.dto.UpdatePessoaDto;
import org.hibernate.sql.Update;

public class PessoaPutDtoRequestBody {

    public static UpdatePessoaDto createdPessoaPutRequestBody(){
        UpdatePessoaDto dto = new UpdatePessoaDto();
        dto.setNome(CreatePessoaTest.pessoaToBeCreated().getNome());
        dto.setDataNascimento(CreatePessoaTest.pessoaToBeCreated().getDataNascimento());
        return dto;
    }
}
