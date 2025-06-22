package br.com.arturbarth.desfsia251.dto;

import br.com.arturbarth.desfsia251.model.entity.PedidoItem;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PedidoItemResponseDTO {
    private Long id;
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal subtotal;

    public PedidoItemResponseDTO(PedidoItem item) {
        this.id = item.getId();
        this.produtoId = item.getProduto().getId();
        this.produtoNome = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.subtotal = item.getSubtotal();
    }
}

