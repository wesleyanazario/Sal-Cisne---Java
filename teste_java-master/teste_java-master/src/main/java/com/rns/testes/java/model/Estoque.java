package com.rns.testes.java.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ESTOQUE")
@Data
public class Estoque extends GenericEntity<Long>{

	@Id
	private Long id;
	@Column
	private int qtd;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_PRODUTO", nullable = true)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "ID_FILIAL", nullable = true)
	private Filial filial;
	
}
