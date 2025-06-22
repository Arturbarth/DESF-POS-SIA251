package br.com.arturbarth.desfsia251.model.repository;

import br.com.arturbarth.desfsia251.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEmail(String email);
    List<Cliente> findByCpf(String cpf);

}