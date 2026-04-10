package livia.prova.Repository;

import com.cm.crud.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<ProdutoModel, ProdutoRepository> {
}