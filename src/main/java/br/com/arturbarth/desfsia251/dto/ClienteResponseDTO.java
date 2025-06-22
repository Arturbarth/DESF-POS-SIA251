package br.com.arturbarth.desfsia251.dto;

import br.com.arturbarth.desfsia251.model.entity.Cliente;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.ativo = cliente.getAtivo();
        this.dataCriacao = cliente.getDataCriacao();
        this.dataAtualizacao = cliente.getDataAtualizacao();
    }
}

