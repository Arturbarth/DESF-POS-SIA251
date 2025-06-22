package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.ClienteRequestDTO;
import br.com.arturbarth.desfsia251.dto.ClienteResponseDTO;
import br.com.arturbarth.desfsia251.exception.ClienteNotFoundException;
import br.com.arturbarth.desfsia251.model.entity.Cliente;
import br.com.arturbarth.desfsia251.model.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImpl clienteService;
    private Cliente cliente;
    private ClienteRequestDTO clienteRequestDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("teste@email.com");
        cliente.setTelefone("123456789");
        cliente.setAtivo(true);

        clienteRequestDTO = new ClienteRequestDTO();
        clienteRequestDTO.setNome("Cliente Teste");
        clienteRequestDTO.setEmail("teste@email.com");
        clienteRequestDTO.setTelefone("123456789");
    }

    @Test
    void testListarTodos() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        List<ClienteResponseDTO> clientes = clienteService.listarTodos();
        assertEquals(1, clientes.size());
        assertEquals("Cliente Teste", clientes.get(0).getNome());
    }

    @Test
    void testBuscarPorId_Sucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        ClienteResponseDTO response = clienteService.buscarPorId(1L);
        assertEquals("Cliente Teste", response.getNome());
    }

    @Test
    void testBuscarPorId_NaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class, () -> clienteService.buscarPorId(1L));
    }

    @Test
    void testSalvar() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteResponseDTO response = clienteService.salvar(clienteRequestDTO);
        assertEquals("Cliente Teste", response.getNome());
    }

    @Test
    void testAtualizar_Sucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteResponseDTO response = clienteService.atualizar(1L, clienteRequestDTO);
        assertEquals("Cliente Teste", response.getNome());
    }

    @Test
    void testAtualizar_NaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class, () -> clienteService.atualizar(1L, clienteRequestDTO));
    }

    @Test
    void testDeletar_Sucesso() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1L);
        assertDoesNotThrow(() -> clienteService.deletar(1L));
    }

    @Test
    void testDeletar_NaoEncontrado() {
        when(clienteRepository.existsById(1L)).thenReturn(false);
        assertThrows(ClienteNotFoundException.class, () -> clienteService.deletar(1L));
    }

    @Test
    void testInativar_Sucesso() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        assertDoesNotThrow(() -> clienteService.inativar(1L));
        verify(clienteRepository).save(cliente);
        assertFalse(cliente.getAtivo());
    }

    @Test
    void testInativar_NaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class, () -> clienteService.inativar(1L));
    }

    @Test
    void testContarClientes() {
        when(clienteRepository.count()).thenReturn(5L);
        assertEquals(5L, clienteService.contarClientes());
    }

    @Test
    void testContarClientesAtivos() {
        when(clienteRepository.count()).thenReturn(3L);
        assertEquals(3L, clienteService.contarClientesAtivos());
    }
}

