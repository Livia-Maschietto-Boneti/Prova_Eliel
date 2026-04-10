package livia.prova.Repository;

import livia.prova.Model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<ProdutoModel, ProdutoRepository> {
}