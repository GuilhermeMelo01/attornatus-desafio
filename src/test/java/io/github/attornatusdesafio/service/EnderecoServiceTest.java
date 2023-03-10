package io.github.attornatusdesafio.service;

import io.github.attornatusdesafio.dto.NewEnderecoDto;
import io.github.attornatusdesafio.enums.TipoEndereco;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.repository.EnderecoRepository;
import io.github.attornatusdesafio.repository.PessoaRepository;
import io.github.attornatusdesafio.util.endereco.CreateEnderecoTest;
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
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository endereroRepositoryMock;
    @Mock
    private PessoaRepository pessoaRepositoryMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(endereroRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(CreateEnderecoTest.enderecoToBeCreated());

        BDDMockito.when(pessoaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(CreatePessoaTest.pessoaToBeCreatedWithEndereco()));

        BDDMockito.when(endereroRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(CreateEnderecoTest.enderecoToBeCreated()));
    }

    @DisplayName("Retorna um endereco inserido partir do idPessoa e NewEnderecoDto")
    @Test
    void retornaEnderecoInseridoApartirIdPessoaENewEnderecoDto_sucesso() {
        Endereco enderecoEsperado = CreateEnderecoTest.enderecoToBeCreated();

        Endereco endereco = enderecoService.insertEndereco(1, new NewEnderecoDto());

        Assertions.assertThat(endereco)
                .isNotNull()
                .isEqualTo(enderecoEsperado);

        Assertions.assertThatCode(() -> enderecoService.insertEndereco(1, new NewEnderecoDto()))
                .doesNotThrowAnyException();
    }

    @DisplayName("Retorna uma pessoa partir do id da pessoa")
    @Test
    void retornaPessoaApartirDoIdPessoa_sucesso() {
        String nomeEsperado = CreatePessoaTest.pessoaToBeCreated().getNome();

        Optional<Pessoa> entity = pessoaRepositoryMock.findById(1);

        Assertions.assertThat(entity)
                .isNotNull()
                .isNotEmpty();

        Assertions.assertThat(entity.get().getNome()).isEqualTo(nomeEsperado);

    }

    @DisplayName("Retorna IllegalArgumentException quando a pessoa n??o possui Id")
    @Test
    void retornaIllegalArgumentExceptionQuandoPessoaNaoPossuiId_sucesso() {
        BDDMockito.when(pessoaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThatIllegalArgumentException()
                        .isThrownBy(() -> enderecoService.defineMainEnderecoByPessoa(1,1));
    }

    @DisplayName("Retorna uma lista de enderecos a partir do id da pessoa")
    @Test
    void retornaUmaListaEnderecosApartirDoIdPessoa_sucesso() {
        String logradouroEsperado = CreateEnderecoTest.enderecoToBeCreated().getLogradouro();

        List<Endereco> enderecos = enderecoService.listAllEnderecoByPessoa(1);

        Assertions.assertThat(enderecos)
                .isNotNull()
                .isNotEmpty();

        Assertions.assertThat(enderecos.get(0).getLogradouro()).isEqualTo(logradouroEsperado);
    }

    @DisplayName("Defini um endereco como main a partir do idPessoa e idEndereco")
    @Test
    void definiUmEnderecoComoMain_sucesso() {
        Endereco endereco = CreateEnderecoTest.enderecoToBeCreated();
        enderecoService.defineMainEnderecoByPessoa(1, endereco.getId());
        endereco.setTipoEndereco(TipoEndereco.PRINCIPAL);

        Assertions.assertThat(endereco.getTipoEndereco()).isEqualTo(TipoEndereco.PRINCIPAL);

        Assertions.assertThatCode(() -> enderecoService.defineMainEnderecoByPessoa(1, 1))
                .doesNotThrowAnyException();
    }
}