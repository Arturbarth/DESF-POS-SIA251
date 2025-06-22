package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.ProdutoRequestDTO;
import br.com.arturbarth.desfsia251.dto.ProdutoResponseDTO;
import br.com.arturbarth.desfsia251.exception.ProdutoNotFoundException;
import br.com.arturbarth.desfsia251.model.entity.Produto;
import br.com.arturbarth.desfsia251.model.repository.ProdutoRepository;
import br.com.arturbarth.desfsia251.model.service.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    private Produto produto;
    private ProdutoRequestDTO produtoRequestDTO;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do produto teste");
        produto.setPreco(new BigDecimal("99.99"));
        produto.setCategoria("Categoria Teste");
        produto.setEstoque(10);
        produto.setAtivo(true);

        produtoRequestDTO = new ProdutoRequestDTO();
        produtoRequestDTO.setNome("Produto Teste");
        produtoRequestDTO.setDescricao("Descrição do produto teste");
        produtoRequestDTO.setPreco(new BigDecimal("99.99"));
        produtoRequestDTO.setCategoria("Categoria Teste");
        produtoRequestDTO.setEstoque(10);
    }

    @Test
    void testListarTodos() {
        List<Produto> produtos = Arrays.asList(produto);
        when(produtoRepository.findAll()).thenReturn(produtos);
        List<ProdutoResponseDTO> resultado = produtoService.listarTodos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getNome());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Sucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        ProdutoResponseDTO resultado = produtoService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals("Produto Teste", resultado.getNome());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_ProdutoNaoEncontrado() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProdutoNotFoundException.class, () -> {
            produtoService.buscarPorId(999L);
        });
        verify(produtoRepository, times(1)).findById(999L);
    }

    @Test
    void testSalvar() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        ProdutoResponseDTO resultado = produtoService.salvar(produtoRequestDTO);
        assertNotNull(resultado);
        assertEquals("Produto Teste", resultado.getNome());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void testAtualizar_Sucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoRequestDTO updateDTO = new ProdutoRequestDTO();
        updateDTO.setNome("Produto Atualizado");
        updateDTO.setDescricao("Descrição atualizada");
        updateDTO.setPreco(new BigDecimal("199.99"));
        updateDTO.setCategoria("Nova Categoria");
        updateDTO.setEstoque(20);
        ProdutoResponseDTO resultado = produtoService.atualizar(1L, updateDTO);
        assertNotNull(resultado);
        verify(produtoRepository, times(1)).findById(1L);
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void testDeletar_Sucesso() {
        when(produtoRepository.existsById(1L)).thenReturn(true);
        produtoService.deletar(1L);
        verify(produtoRepository, times(1)).existsById(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletar_ProdutoNaoEncontrado() {
        when(produtoRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(ProdutoNotFoundException.class, () -> {
            produtoService.deletar(999L);
        });
        verify(produtoRepository, times(1)).existsById(999L);
        verify(produtoRepository, never()).deleteById(anyLong());
    }

    @Test
    void testContarProdutos() {
        when(produtoRepository.count()).thenReturn(5L);
        long resultado = produtoService.contarProdutos();
        assertEquals(5L, resultado);
        verify(produtoRepository, times(1)).count();
    }
}
