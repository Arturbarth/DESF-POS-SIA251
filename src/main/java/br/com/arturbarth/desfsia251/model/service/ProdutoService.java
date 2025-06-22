package br.com.arturbarth.desfsia251.model.service;

import br.com.arturbarth.desfsia251.dto.ProdutoRequestDTO;
import br.com.arturbarth.desfsia251.dto.ProdutoResponseDTO;

import java.util.List;

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
