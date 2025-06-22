package br.com.arturbarth.desfsia251.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PedidoItemRequestDTO {
    private Long produtoId;
    private Integer quantidade;
}

