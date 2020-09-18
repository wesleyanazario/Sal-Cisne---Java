package com.rns.testes.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rns.testes.java.model.Estoque;

@Repository
public interface IEstoqueDao extends JpaRepository<Estoque, Long>{
	
	@Query(value = "SELECT E.id FROM Estoque as E where E.filial.id = :id and E.produto.id = :idProd")
	public Long findEstoque(Long id, String idProd);
}
