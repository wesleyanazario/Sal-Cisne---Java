package com.rns.testes.java.business;
import com.rns.testes.java.dto.ProdutoDto;
import com.rns.testes.java.model.Estoque;

public class EstoqueBusiness {
	
	public int Transferencia(Estoque estoqueOrigem,Estoque estoqueDestino, ProdutoDto produto, int qtd) {
	
		if(estoqueOrigem != null && estoqueOrigem.getProduto().getId().equalsIgnoreCase(produto.getId()) && estoqueOrigem.getQtd() >= qtd) {
			
			if(estoqueDestino != null && estoqueDestino.getProduto().getId().equalsIgnoreCase(produto.getId())) {

				return 1;
			}else {
				return 0;
			}
		}else {
			return -1;
		}
		
	}
}
