package br.com.arturbarth.desfsia251.model.service;

import br.com.arturbarth.desfsia251.dto.PedidoRequestDTO;
import br.com.arturbarth.desfsia251.dto.PedidoResponseDTO;
import br.com.arturbarth.desfsia251.model.exception.ClienteNotFoundException;
import br.com.arturbarth.desfsia251.model.exception.PedidoNotFoundException;
import br.com.arturbarth.desfsia251.model.exception.ProdutoNotFoundException;
import br.com.arturbarth.desfsia251.model.entity.Cliente;
import br.com.arturbarth.desfsia251.model.entity.Pedido;
import br.com.arturbarth.desfsia251.model.entity.PedidoItem;
import br.com.arturbarth.desfsia251.model.entity.Produto;
import br.com.arturbarth.desfsia251.model.repository.ClienteRepository;
import br.com.arturbarth.desfsia251.model.repository.PedidoRepository;
import br.com.arturbarth.desfsia251.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado com ID: " + id));
        return new PedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoDTO) {
        Pedido pedido = converterDTOParaEntidade(pedidoDTO);
        pedido.calcularValorTotal();
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedidoSalvo);
    }

    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedidoDTO) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado com ID: " + id));
        alimentarCamposDoPedido(pedidoDTO, pedidoExistente);
        pedidoExistente.calcularValorTotal();
        Pedido pedidoAtualizado = pedidoRepository.save(pedidoExistente);
        return new PedidoResponseDTO(pedidoAtualizado);
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNotFoundException("Pedido não encontrado com ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long contarPedidos() {
        return pedidoRepository.count();
    }

    private Pedido converterDTOParaEntidade(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        alimentarCamposDoPedido(dto, pedido);
        return pedido;
    }

    private void alimentarCamposDoPedido(PedidoRequestDTO dto, Pedido pedido) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + dto.getClienteId()));
        pedido.setCliente(cliente);
        List<PedidoItem> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com ID: " + itemDTO.getProdutoId()));
            PedidoItem item = new PedidoItem();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPedido(pedido);
            return item;
        }).toList();
        pedido.setItens(itens);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPedidosPorCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + clienteId));
        List<Pedido> pedidos = pedidoRepository.findByCliente(cliente);
        return pedidos.stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }
}

