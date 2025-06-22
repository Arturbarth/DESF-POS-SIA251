package br.com.arturbarth.desfsia251.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {
    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Itens do pedido são obrigatórios")
    private List<PedidoItemRequestDTO> itens;
}

