package br.com.arturbarth.desfsia251.model.repository;

import br.com.arturbarth.desfsia251.model.entity.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
}