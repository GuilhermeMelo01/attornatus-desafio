package io.github.attornatusdesafio.controller;

import io.github.attornatusdesafio.dto.NewEnderecoDto;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.service.EnderecoService;
import io.github.attornatusdesafio.util.endereco.CreateEnderecoTest;
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
class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoService enderecoServiceMock;
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp(){
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Endereco> listaEnderecos = List.of(CreateEnderecoTest.enderecoToBeCreated());
        BDDMockito.when(enderecoServiceMock.listAllEnderecoByPessoa(ArgumentMatchers.any()))
                .thenReturn(listaEnderecos);

        BDDMockito.when(enderecoServiceMock.insertEndereco(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(CreateEnderecoTest.enderecoToBeCreated());

        BDDMockito.doNothing().when(enderecoServiceMock)
                .defineMainEnderecoByPessoa(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @DisplayName("Retorna uma lista de enderecos a partir do Id de pessoa")
    @Test
    void retornaListaEnderecosApartirIdPessoaQuandoTiverSucesso(){
        String logradouroEsperado = CreateEnderecoTest.enderecoToBeCreated().getLogradouro();
        List<Endereco> listaEnderecos = enderecoController.listAllEnderecoByPessoa(1).getBody();

        Assertions.assertThat(listaEnderecos)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listaEnderecos.get(0).getLogradouro()).isEqualTo(logradouroEsperado);
    }

    @DisplayName("Insere um endereco a partir do Id da pessoa")
    @Test
    void criaEnderecosPartirDoIdPessoaQuandoTiverSucesso(){
        Assertions.assertThatCode(() -> enderecoController.insertEndereco(1, new NewEnderecoDto()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entityEndereco = enderecoController.insertEndereco(1,
                new NewEnderecoDto());

        Assertions.assertThat(entityEndereco).isNotNull();

        Assertions.assertThat(entityEndereco.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @DisplayName("Defini o tipo de endereco para Main")
    @Test
    void atualizaPessoaQuandoTiverSucesso(){

        Assertions.assertThatCode(() -> enderecoServiceMock.defineMainEnderecoByPessoa(1, 1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entityPessoa = enderecoController.defineMainEnderecoByPessoa(1, 1);

        Assertions.assertThat(entityPessoa).isNotNull();

        Assertions.assertThat(entityPessoa.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}