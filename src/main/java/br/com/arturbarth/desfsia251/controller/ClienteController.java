package br.com.arturbarth.desfsia251.controller;

import br.com.arturbarth.desfsia251.dto.ClienteRequestDTO;
import br.com.arturbarth.desfsia251.dto.ClienteResponseDTO;
import br.com.arturbarth.desfsia251.model.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteResponseDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        ClienteResponseDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/contar")
    public ResponseEntity<Map<String, Long>> contarClientes() {
        long total = clienteService.contarClientes();
        long ativos = clienteService.contarClientesAtivos();
        Map<String, Long> contadores = Map.of(
                "total", total,
                "ativos", ativos
        );
        return ResponseEntity.ok(contadores);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO clienteDTO) {
        ClienteResponseDTO clienteCriado = clienteService.salvar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO clienteDTO) {
        ClienteResponseDTO clienteAtualizado = clienteService.atualizar(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarCliente(@PathVariable Long id) {
        clienteService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}

