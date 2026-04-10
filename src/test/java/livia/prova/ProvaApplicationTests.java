package livia.prova;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProvaApplicationTests {

	@Test
	void contextLoads() {
	}

}
dontpad.com/livcu

		MODEL

        package com.cm.crud.models;

import com.cm.crud.enums.StatusProduto;
import jakarta.persistence.*;

@Entity
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String descricao;
	private Double preco;

	@Enumerated(EnumType.STRING)
	private StatusProduto status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public StatusProduto getStatus() {
		return status;
	}

	public void setStatus(StatusProduto status) {
		this.status = status;
	}
}

ENUM

package com.cm.crud.enums;

public enum StatusProduto {
	DISPONIVEL,
	ESGOTADO,
	INATIVO
}

REPOSITORY

package com.cm.crud.repositories;

import com.cm.crud.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}

CONTROLLER

package com.cm.crud.controller;

import com.cm.crud.models.ProdutoModel;
import com.cm.crud.services.ProdutoService;
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
		ProdutoModel produto = service.buscar(id);

		if(produto == null){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoModel> atualizar(@PathVariable Long id, @RequestBody ProdutoModel produto){
		ProdutoModel existente = service.buscar(id);

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

SERVICE

package com.cm.crud.services;

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