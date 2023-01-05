package io.github.attornatusdesafio.enums;

public enum TipoEndereco {

    PRINCIPAL(1, "Principal"),
    SECUNDARIO(2, "Secundario");

    private final Integer cod;
    private final String descricao;

    TipoEndereco(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
}
