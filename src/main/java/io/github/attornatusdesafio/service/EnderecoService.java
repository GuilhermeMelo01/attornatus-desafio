package io.github.attornatusdesafio.service;

import io.github.attornatusdesafio.dto.NewEnderecoDto;
import io.github.attornatusdesafio.enums.TipoEndereco;
import io.github.attornatusdesafio.model.Endereco;
import io.github.attornatusdesafio.model.Pessoa;
import io.github.attornatusdesafio.repository.EnderecoRepository;
import io.github.attornatusdesafio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Endereco insertEndereco(Integer pessoaId, NewEnderecoDto dto) {
        //Buscar a pessoa pelo Id no metodo privado da classe
        Pessoa pessoa = findPessoaById(pessoaId);

        //Criar endereco a partir do construtor
        Endereco endereco = new Endereco(dto.getLogradouro(), dto.getCep(),
                dto.getNumero(), dto.getCidade(), List.of(pessoa));
        //adicionar o novo endereco à pessoa
        pessoa.getEnderecos().add(endereco);

        //salvar o endereco e depois salvar a pessoa de acordo com o seu endereco
        endereco = enderecoRepository.save(endereco);
        pessoaRepository.saveAll(endereco.getPessoas());
        return endereco;
    }

    public List<Endereco> listAllEnderecoByPessoa(Integer pessoaId) {
        //Retorna apenas os enderecos da pessoa buscada pelo Id.
        return findPessoaById(pessoaId).getEnderecos();
    }
    public void defineMainEnderecoByPessoa(Integer pessoaId, Integer enderecoId) {
        //Pego a pessoa pelo Id passado no paramentro com o metodo privado da classe
        Pessoa pessoa = findPessoaById(pessoaId);

        //Percorrer a lista de enderecos para ver se já tem um endereco Principal.
        for (Endereco endereco : pessoa.getEnderecos()) {
            //Se já tiver um endereco principal, ele sera modificado para secundario.
            if (endereco.getTipoEndereco().equals(TipoEndereco.PRINCIPAL)) {
                endereco.setTipoEndereco(TipoEndereco.SECUNDARIO);
            }
        }

        //Pego o endereco pelo Id passado no paramentro, se não existir é lançada uma exception.
        Endereco endereco = enderecoRepository.findById(enderecoId).orElseThrow(
                () -> new IllegalArgumentException("Endereco não existe!"));

        //E modifico o endereco passado para principal.
        endereco.setTipoEndereco(TipoEndereco.PRINCIPAL);
        enderecoRepository.save(endereco);
    }

    private Pessoa findPessoaById(Integer id){
        return pessoaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Não existe pessoa com esse Id: "+ id));
    }
}
