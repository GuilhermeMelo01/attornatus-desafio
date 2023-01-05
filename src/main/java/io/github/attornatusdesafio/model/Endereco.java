package io.github.attornatusdesafio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.attornatusdesafio.enums.TipoEndereco;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    private TipoEndereco tipoEndereco;

    @JsonIgnore
    @ManyToMany(mappedBy = "enderecos")
    private List<Pessoa> pessoas = new ArrayList<>();

    public Endereco() {
        this.tipoEndereco = TipoEndereco.SECUNDARIO;
    }

    public Endereco(String logradouro, String cep, String numero, String cidade, List<Pessoa> pessoas) {
        this.tipoEndereco = TipoEndereco.SECUNDARIO;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.pessoas = pessoas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(logradouro, endereco.logradouro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro);
    }
}
