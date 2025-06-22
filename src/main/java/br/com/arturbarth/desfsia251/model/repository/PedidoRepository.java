package br.com.arturbarth.desfsia251.model.repository;

import br.com.arturbarth.desfsia251.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}