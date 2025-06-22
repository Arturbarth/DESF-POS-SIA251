package br.com.arturbarth.desfsia251.dto;

import br.com.arturbarth.desfsia251.model.entity.Pedido;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoResponseDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private List<PedidoItemResponseDTO> itens;
    private BigDecimal valorTotal;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = new ClienteResponseDTO(pedido.getCliente());
        this.itens = pedido.getItens().stream().map(PedidoItemResponseDTO::new).toList();
        this.valorTotal = pedido.getValorTotal();
    }
}

