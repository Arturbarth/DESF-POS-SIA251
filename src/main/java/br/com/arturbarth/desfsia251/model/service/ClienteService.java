package br.com.arturbarth.desfsia251.model.service;

import br.com.arturbarth.desfsia251.dto.ClienteRequestDTO;
import br.com.arturbarth.desfsia251.dto.ClienteResponseDTO;
import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> listarTodos();
    ClienteResponseDTO buscarPorId(Long id);
    ClienteResponseDTO salvar(ClienteRequestDTO clienteDTO);
    ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDTO);
    void deletar(Long id);
    void inativar(Long id);
    long contarClientes();
    long contarClientesAtivos();
}

