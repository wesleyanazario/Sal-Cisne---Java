package com.rns.testes.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rns.testes.java.business.EstoqueBusiness;
import com.rns.testes.java.dao.IEstoqueDao;
import com.rns.testes.java.dto.EstoqueDto;
import com.rns.testes.java.dto.FilialDto;
import com.rns.testes.java.dto.ProdutoDto;
import com.rns.testes.java.dto.mapper.EstoqueMapper;
import com.rns.testes.java.dto.mapper.FilialMapper;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.impl.EstoqueService;

@CrossOrigin
@RestController
@RequestMapping
public class EstoqueController {

	private static final String BASE_URL = "estoque/";
	@Autowired
	IEstoqueService service;
	@Autowired
	IEstoqueDao dao;
	
	@GetMapping(value = BASE_URL + "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Estoque>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
	
	@PutMapping(value = BASE_URL + "transfer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> transferencia(@RequestParam(name = "idOrigem") Long idOrigem,
			@RequestParam(name = "idDestino") Long idDestino,
			@RequestParam(name = "qtd") int qtd,@RequestBody ProdutoDto produto) {
		
		Long idEstoqueOrigem = dao.findEstoque(idOrigem,produto.getId());
		Estoque estoqueOrigem = service.findById(idEstoqueOrigem);
		
		Long idEstoqueDestino = dao.findEstoque(idDestino,produto.getId());
		Estoque estoqueDestino = service.findById(idEstoqueDestino);
		if((new EstoqueBusiness().Transferencia(estoqueOrigem, estoqueDestino, produto, qtd) == 1)) {
			System.out.println("FAZENDO TRANSFERENCIA ENTRE FILIAIS");
			System.out.println(estoqueOrigem);
			System.out.println(estoqueDestino);
			estoqueOrigem.setQtd((estoqueOrigem.getQtd()-qtd));
			estoqueDestino.setQtd((qtd+estoqueDestino.getQtd()));
			System.out.println(estoqueOrigem);
			System.out.println(estoqueDestino);
			service.save(estoqueOrigem);
			service.save(estoqueDestino);
			return ResponseEntity.ok("Sucesso");
		}
		return ResponseEntity.ok("Erro");
	}
	@GetMapping(value = BASE_URL + "find-by-id", produces = MediaType.APPLICATION_JSON_VALUE, params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Estoque> findById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
	
}
