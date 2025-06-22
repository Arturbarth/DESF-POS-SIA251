package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.PedidoItemRequestDTO;
import br.com.arturbarth.desfsia251.dto.PedidoRequestDTO;
import br.com.arturbarth.desfsia251.dto.PedidoResponseDTO;
import br.com.arturbarth.desfsia251.exception.ClienteNotFoundException;
import br.com.arturbarth.desfsia251.exception.PedidoNotFoundException;
import br.com.arturbarth.desfsia251.exception.ProdutoNotFoundException;
import br.com.arturbarth.desfsia251.model.entity.Cliente;
import br.com.arturbarth.desfsia251.model.entity.Pedido;
import br.com.arturbarth.desfsia251.model.entity.PedidoItem;
import br.com.arturbarth.desfsia251.model.entity.Produto;
import br.com.arturbarth.desfsia251.model.repository.ClienteRepository;
import br.com.arturbarth.desfsia251.model.repository.PedidoRepository;
import br.com.arturbarth.desfsia251.model.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Pedido pedido;
    private PedidoRequestDTO pedidoRequestDTO;
    private Cliente cliente;
    private Produto produto;
    private PedidoItem pedidoItem;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");

        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(new BigDecimal("10.00"));

        pedidoItem = new PedidoItem();
        pedidoItem.setProduto(produto);
        pedidoItem.setQuantidade(2);

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setItens(Collections.singletonList(pedidoItem));

        PedidoItemRequestDTO itemDTO = new PedidoItemRequestDTO();
        itemDTO.setProdutoId(1L);
        itemDTO.setQuantidade(2);

        pedidoRequestDTO = new PedidoRequestDTO();
        pedidoRequestDTO.setClienteId(1L);
        pedidoRequestDTO.setItens(Collections.singletonList(itemDTO));
    }

    @Test
    void testListarTodos() {
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));
        List<PedidoResponseDTO> pedidos = pedidoService.listarTodos();
        assertEquals(1, pedidos.size());
        assertEquals(1L, pedidos.get(0).getId());
    }

    @Test
    void testBuscarPorId_Sucesso() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        PedidoResponseDTO response = pedidoService.buscarPorId(1L);
        assertEquals(1L, response.getId());
    }

    @Test
    void testBuscarPorId_NaoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PedidoNotFoundException.class, () -> pedidoService.buscarPorId(1L));
    }

    @Test
    void testSalvar() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        PedidoResponseDTO response = pedidoService.salvar(pedidoRequestDTO);
        assertEquals(1L, response.getId());
    }

    @Test
    void testSalvar_ClienteNaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class, () -> pedidoService.salvar(pedidoRequestDTO));
    }

    @Test
    void testSalvar_ProdutoNaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProdutoNotFoundException.class, () -> pedidoService.salvar(pedidoRequestDTO));
    }

    @Test
    void testAtualizar_Sucesso() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        PedidoResponseDTO response = pedidoService.atualizar(1L, pedidoRequestDTO);
        assertEquals(1L, response.getId());
    }

    @Test
    void testAtualizar_PedidoNaoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PedidoNotFoundException.class, () -> pedidoService.atualizar(1L, pedidoRequestDTO));
    }

    @Test
    void testDeletar_Sucesso() {
        when(pedidoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(1L);
        assertDoesNotThrow(() -> pedidoService.deletar(1L));
    }

    @Test
    void testDeletar_NaoEncontrado() {
        when(pedidoRepository.existsById(1L)).thenReturn(false);
        assertThrows(PedidoNotFoundException.class, () -> pedidoService.deletar(1L));
    }

    @Test
    void testContarPedidos() {
        when(pedidoRepository.count()).thenReturn(7L);
        assertEquals(7L, pedidoService.contarPedidos());
    }
}

