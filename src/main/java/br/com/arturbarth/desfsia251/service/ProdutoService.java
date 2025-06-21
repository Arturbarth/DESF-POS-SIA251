package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.ProdutoRequestDTO;
import br.com.arturbarth.desfsia251.dto.ProdutoResponseDTO;
import br.com.arturbarth.desfsia251.exception.ProdutoNotFoundException;
import br.com.arturbarth.desfsia251.model.Produto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface ProdutoService {

    List<ProdutoResponseDTO> listarTodos();
    ProdutoResponseDTO buscarPorId(Long id);
    List<ProdutoResponseDTO> buscarPorNome(String nome);
    List<ProdutoResponseDTO> buscarPorCategoria(String categoria);
    List<ProdutoResponseDTO> listarDisponiveis();
    long contarProdutos();
    long contarProdutosAtivos();
    ProdutoResponseDTO salvar(ProdutoRequestDTO produtoDTO);
    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDTO);
    void deletar(Long id);
    void inativar(Long id);

}
