package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.PedidoRequestDTO;
import br.com.arturbarth.desfsia251.dto.PedidoResponseDTO;
import java.util.List;

public interface PedidoService {
    List<PedidoResponseDTO> listarTodos();
    PedidoResponseDTO buscarPorId(Long id);
    PedidoResponseDTO salvar(PedidoRequestDTO pedidoDTO);
    PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedidoDTO);
    void deletar(Long id);
    long contarPedidos();

    List<PedidoResponseDTO> listarPedidosPorCliente(Long clienteId);
}

