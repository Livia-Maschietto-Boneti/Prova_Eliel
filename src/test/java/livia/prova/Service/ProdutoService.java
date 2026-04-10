package livia.prova.Service;

import com.cm.crud.models.ProdutoModel;
import com.cm.crud.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoModel criar(ProdutoModel produto){
        return repository.save(produto);
    }

    public List<ProdutoModel> listar(){
        return repository.findAll();
    }

    public ProdutoModel buscar(Long id){
        return repository.findById(id).orElse(null);
    }

    public ProdutoModel atualizar(Long id, ProdutoModel produto){
        produto.setId(id);
        return repository.save(produto);
    }

    public boolean deletar(Long id){
        ProdutoModel produto = buscar(id);
        if(produto == null){
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}