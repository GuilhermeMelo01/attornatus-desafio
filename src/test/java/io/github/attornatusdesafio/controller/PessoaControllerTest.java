package io.github.attornatusdesafio.controller;

import io.github.attornatusdesafio.dto.NewPessoaDto;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.service.PessoaService;
import io.github.attornatusdesafio.util.pessoa.CreatePessoaTest;
import io.github.attornatusdesafio.util.pessoa.PessoaPostDtoRequestBody;
import io.github.attornatusdesafio.util.pessoa.PessoaPutDtoRequestBody;
import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;


@ExtendWith(SpringExtension.class)
class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaServiceMock;
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp(){
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Pessoa> listaPessoas = List.of(CreatePessoaTest.pessoaToBeCreated());
        BDDMockito.when(pessoaServiceMock.listAll())
                .thenReturn(listaPessoas);

        BDDMockito.when(pessoaServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(CreatePessoaTest.pessoaToBeCreated());

        BDDMockito.when(pessoaServiceMock.insert(ArgumentMatchers.any(NewPessoaDto.class)))
                .thenReturn(CreatePessoaTest.pessoaToBeCreated());

        BDDMockito.doNothing().when(pessoaServiceMock).update(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @DisplayName("Retorna uma lista de pessoas")
    @Test
    void retornaListaPessoasQuandoTiverSucesso(){
        String nomeEsperado = CreatePessoaTest.pessoaToBeSaved().getNome();
        List<Pessoa> listaPessoas = pessoaController.findAll().getBody();

        Assertions.assertThat(listaPessoas)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listaPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @DisplayName("Retorna uma pessoa a partir do Id")
    @Test
    void retornaPessoasPartirDoIdQuandoTiverSucesso(){
        Integer idEsperado = CreatePessoaTest.pessoaToBeCreated().getId();
        Pessoa pessoaEsperada = CreatePessoaTest.pessoaToBeCreated();
        Pessoa pessoa = pessoaController.findById(100).getBody();

        Assertions.assertThat(pessoa)
                .isNotNull()
                .isEqualTo(pessoaEsperada);

        Assertions.assertThat(pessoa.getId()).isEqualTo(idEsperado);
    }

    @DisplayName("Cria uma pessoa a partir do NewPessoaDto")
    @Test
    void CriaPessoaQuandoTiverSucesso(){

        Assertions.assertThatCode(() -> pessoaController.insert(
                PessoaPostDtoRequestBody.createdPessoaPostRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entityPessoa = pessoaController.insert(
                PessoaPostDtoRequestBody.createdPessoaPostRequestBody());

        Assertions.assertThat(entityPessoa).isNotNull();

        Assertions.assertThat(entityPessoa.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @DisplayName("Atualiza a pessoa a partir do Id e UpdatePessoaDto")
    @Test
    void atualizaPessoaQuandoTiverSucesso(){

        Assertions.assertThatCode(() -> pessoaController.update(1,
                        PessoaPutDtoRequestBody.createdPessoaPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entityPessoa = pessoaController.update(1,
                PessoaPutDtoRequestBody.createdPessoaPutRequestBody());

        Assertions.assertThat(entityPessoa).isNotNull();

        Assertions.assertThat(entityPessoa.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}