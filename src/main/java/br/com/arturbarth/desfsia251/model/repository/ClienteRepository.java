package br.com.arturbarth.desfsia251.model.repository;

import br.com.arturbarth.desfsia251.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}