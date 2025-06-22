package br.com.arturbarth.desfsia251.model.repository;

import br.com.arturbarth.desfsia251.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByCategoriaIgnoreCase(String categoria);

    List<Produto> findByAtivoTrue();

    List<Produto> findByEstoqueGreaterThan(Integer estoque);

    List<Produto> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    @Query("SELECT p FROM Produto p WHERE p.ativo = true AND p.estoque > 0")
    List<Produto> findProdutosDisponiveis();

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.ativo = true")
    long countProdutosAtivos();

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:termo% OR p.descricao LIKE %:termo%")
    List<Produto> buscarPorTermo(@Param("termo") String termo);

}
