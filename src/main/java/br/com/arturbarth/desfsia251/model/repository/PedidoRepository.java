package br.com.arturbarth.desfsia251.model.repository;

import br.com.arturbarth.desfsia251.model.entity.Cliente;
import br.com.arturbarth.desfsia251.model.entity.Pedido;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

}