package br.com.fatec.catalogo.repositories;

import br.com.fatec.catalogo.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    boolean existsByNome(String nome);

    List<ProdutoModel> findAllByOrderByDataCadastroDesc();
    List<ProdutoModel> findByNomeContainingIgnoreCaseOrderByDataCadastroDesc(String nome);
    List<ProdutoModel> findByCategoriaIdCategoriaOrderByDataCadastroDesc(Long idCategoria);
    List<ProdutoModel> findAllByOrderByIdProdutoAsc();
    List<ProdutoModel> findByNomeContainingIgnoreCaseOrderByIdProdutoAsc(String nome);
    List<ProdutoModel> findByCategoriaIdCategoriaOrderByIdProdutoAsc(Long idCategoria);
}
