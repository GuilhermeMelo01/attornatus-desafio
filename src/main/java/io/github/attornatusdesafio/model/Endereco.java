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

    public Endereco(Integer id, String logradouro, String cep, String numero,
                    String cidade, List<Pessoa> pessoas) {
        this.tipoEndereco = TipoEndereco.SECUNDARIO;
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.pessoas = pessoas;
    }

    public Integer getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
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
