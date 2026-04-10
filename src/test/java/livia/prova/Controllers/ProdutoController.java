package livia.prova.Controllers;

import livia.prova.Model.ProdutoModel;
import livia.prova.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoModel> criar(@RequestBody ProdutoModel produto){
        ProdutoModel novo = service.criar(produto);

        URI uri = URI.create("/api/produtos/" + novo.getId());

        return ResponseEntity.created(uri).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscar(@PathVariable Long id){
        ProdutoModel produto = service.buscarPorId(id);

        if(produto == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizar(@PathVariable Long id, @RequestBody ProdutoModel produto){
        ProdutoModel existente = service.buscarPorId(id);

        if(existente == null){
            return ResponseEntity.notFound().build();
        }

        ProdutoModel atualizado = service.atualizar(id, produto);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        boolean deletado = service.deletar(id);

        if(!deletado){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}
