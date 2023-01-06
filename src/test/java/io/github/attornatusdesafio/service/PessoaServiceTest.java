package io.github.attornatusdesafio.service;

import io.github.attornatusdesafio.dto.NewPessoaDto;
import io.github.attornatusdesafio.dto.UpdatePessoaDto;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.repository.PessoaRepository;
import io.github.attornatusdesafio.util.pessoa.CreatePessoaTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PessoaServiceTest {


    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepositoryMock;

    @BeforeEach
    void setUp(){

        List<Pessoa> listaPessoas = List.of(CreatePessoaTest.pessoaToBeCreated());
        BDDMockito.when(pessoaRepositoryMock.findAll())
                .thenReturn(listaPessoas);

        BDDMockito.when(pessoaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(CreatePessoaTest.pessoaToBeCreated()));

        BDDMockito.when(pessoaRepositoryMock.save(new Pessoa()))
                .thenReturn(CreatePessoaTest.pessoaToBeCreated());
    }

    @DisplayName("Sucesso quando retornar uma lista de pessoas")
    @Test
    void retornaListaPessoasQuandoTiverSucesso(){
        String nomeEsperado = CreatePessoaTest.pessoaToBeSaved().getNome();
        List<Pessoa> listaPessoas = pessoaService.listAll();

        Assertions.assertThat(listaPessoas)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listaPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @DisplayName("Sucesso quando retornar uma pessoa a partir do Id")
    @Test
    void retornaPessoasPartirDoIdQuandoTiverSucesso(){
        Integer idEsperado = CreatePessoaTest.pessoaToBeCreated().getId();
        Pessoa pessoaEsperada = CreatePessoaTest.pessoaToBeCreated();
        Pessoa pessoa = pessoaService.findById(1);

        Assertions.assertThat(pessoa)
                .isNotNull()
                .isEqualTo(pessoaEsperada);

        Assertions.assertThat(pessoa.getId()).isEqualTo(idEsperado);
    }

    @DisplayName("Sucesso quando for criada uma pessoa")
    @Test
    void CriaPessoaQuandoTiverSucesso(){
        Pessoa pessoaEsperada = CreatePessoaTest.pessoaToBeCreated();
        Assertions.assertThatCode(() -> pessoaService.insert(new NewPessoaDto()))
                .doesNotThrowAnyException();

        Pessoa pessoa = pessoaService.insert(new NewPessoaDto());

        Assertions.assertThat(pessoa)
                .isNotNull()
                .isEqualTo(pessoaEsperada);
    }

    @DisplayName("Sucesso quando for atualizado com sucesso a partir do Id pessoa")
    @Test
    void AtualizaPessoaApartirDoIdQuandoTiverSucesso(){
        Assertions.assertThatCode(() -> pessoaService.update(1, new UpdatePessoaDto()))
                .doesNotThrowAnyException();
    }
}