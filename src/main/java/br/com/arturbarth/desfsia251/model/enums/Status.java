package br.com.arturbarth.desfsia251.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDENTE("Pendente"),
    FATURADO("Faturado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}