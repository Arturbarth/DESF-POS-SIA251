package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.ClienteRequestDTO;
import br.com.arturbarth.desfsia251.dto.ClienteResponseDTO;
import br.com.arturbarth.desfsia251.exception.ClienteNotFoundException;
import br.com.arturbarth.desfsia251.model.entity.Cliente;
import br.com.arturbarth.desfsia251.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + id));
        return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteDTO) {
        Cliente cliente = converterDTOParaEntidade(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponseDTO(clienteSalvo);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + id));
        alimentarCamposDoCliente(clienteDTO, clienteExistente);
        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        return new ClienteResponseDTO(clienteAtualizado);
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public void inativar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + id));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public long contarClientes() {
        return clienteRepository.count();
    }

    @Transactional(readOnly = true)
    public long contarClientesAtivos() {
        return clienteRepository.count();
    }

    private Cliente converterDTOParaEntidade(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        alimentarCamposDoCliente(dto, cliente);
        cliente.setAtivo(true);
        return cliente;
    }

    private static void alimentarCamposDoCliente(ClienteRequestDTO dto, Cliente cliente) {
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
    }

    @Override
    public List<ClienteResponseDTO> buscarPorCpf(String nome) {
        return clienteRepository.findByCpf(nome).stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }

    @Override
    public List<ClienteResponseDTO> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email).stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }
}

