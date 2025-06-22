package br.com.arturbarth.desfsia251.model.entity;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> itens;

    @Column(name = "valor_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    public void calcularValorTotal() {
        if (itens != null) {
            this.valorTotal = itens.stream()
                    .map(PedidoItem::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorTotal = BigDecimal.ZERO;
        }
    }
}
