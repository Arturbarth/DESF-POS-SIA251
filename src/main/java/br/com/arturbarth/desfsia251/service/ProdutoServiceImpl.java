package br.com.arturbarth.desfsia251.service;

import br.com.arturbarth.desfsia251.dto.ProdutoRequestDTO;
import br.com.arturbarth.desfsia251.dto.ProdutoResponseDTO;
import br.com.arturbarth.desfsia251.exception.ProdutoNotFoundException;
import br.com.arturbarth.desfsia251.model.entity.Produto;
import br.com.arturbarth.desfsia251.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    public static final String PRODUTO_NAO_ENCONTRADO_COM_ID = "Produto não encontrado com ID: ";
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(PRODUTO_NAO_ENCONTRADO_COM_ID + id));
        return new ProdutoResponseDTO(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorCategoria(String categoria) {
        List<Produto> produtos = produtoRepository.findByCategoriaIgnoreCase(categoria);
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarDisponiveis() {
        List<Produto> produtos = produtoRepository.findProdutosDisponiveis();
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public long contarProdutos() {
        return produtoRepository.count();
    }

    @Transactional(readOnly = true)
    public long contarProdutosAtivos() {
        return produtoRepository.countProdutosAtivos();
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoDTO) {
        Produto produto = converterDTOParaEntidade(produtoDTO);
        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produtoSalvo);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(PRODUTO_NAO_ENCONTRADO_COM_ID + id));

        alimentarCamposDoProduto(produtoDTO, produtoExistente);

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoAtualizado);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException(PRODUTO_NAO_ENCONTRADO_COM_ID + id);
        }
        produtoRepository.deleteById(id);
    }

    public void inativar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(PRODUTO_NAO_ENCONTRADO_COM_ID + id));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    private Produto converterDTOParaEntidade(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        alimentarCamposDoProduto(dto, produto);
        produto.setAtivo(true);
        return produto;
    }

    private static void alimentarCamposDoProduto(ProdutoRequestDTO dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setEstoque(dto.getEstoque());
    }
}
