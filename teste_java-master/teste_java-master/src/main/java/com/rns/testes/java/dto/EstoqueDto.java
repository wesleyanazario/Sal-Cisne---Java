package com.rns.testes.java.dto;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.GenericEntity;
import com.rns.testes.java.model.Produto;

import lombok.Data;

@Data
public class EstoqueDto extends GenericEntity<Long>{
	private Long id;
	private int qtd;
	private Produto produto;
	private Filial filial;
	
}
