package livia.prova.Service;

import livia.prova.Model.ProdutoModel;
import livia.prova.Repository.ProdutoRepository;
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

    public ProdutoModel buscarPorId(Long id){
        return repository.findById(id).get();
    }

    public ProdutoModel atualizar(Long id, ProdutoModel produto){
        produto.setId(id);
        return repository.save(produto);
    }

    public boolean deletar(Long id){
        repository.deleteById(id);
        return true;
    }

}