package com.rns.testes.java.seeder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rns.testes.java.enums.EnumTipoFilial;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import com.zaxxer.hikari.util.ClockSource.Factory;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EstoqueSeeder {

	@Autowired
	IEstoqueService service;
	
	@Autowired
	IProdutoService Pservice;
	
	@Autowired
    IFilialService Fservice;
	
	@EventListener
	public void seedEstoque(ContextRefreshedEvent event) {
        try {
            log.info("Criando estoques....");
            criandoEstoque();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
	
	private void criandoEstoque() {
		
		Random rand = new Random();
    	//Carregando Produtos
    	List<Produto> produtos = new ArrayList<Produto>();
    	 for (int i = 1; i < 25; i++) {
             Produto produto = new Produto();
             produto.setId("Cod-Produto-"+i);
             produto.setNome("Sal produto " + i);
             
             Pservice.save(produto);
         }
    	 produtos.addAll(Pservice.findAll());
    	 
		//Criando Filial para estoques;
    	 
    	//Criando Filial - 01
		Filial galpao = new Filial();
        galpao.setRazaoSocial("Galpão de estocagem de produtos LTDA");
        galpao.setCnpj("83.230.227/0001-50");
        galpao.setTipoFilial(EnumTipoFilial.DEPOSITO);
        galpao.setEndereco("Rua Teste Galpao 187, São Paulo - SP");
        Fservice.save(galpao);
      
        //Criando Filial - 02
        Filial lojaBtoC1 = new Filial();
        lojaBtoC1.setRazaoSocial("Loja São Paulo LTDA");
        lojaBtoC1.setCnpj("72.286.494/0001-23");
        lojaBtoC1.setTipoFilial(EnumTipoFilial.LOJA_PF);
        lojaBtoC1.setEndereco("Av. Paulista, 111, São Paulo - SP");
        Fservice.save(lojaBtoC1);
        
        //Criando Filial - 03
        Filial lojaBtoC2 = new Filial();
        lojaBtoC2.setRazaoSocial("Loja Rio de Janeiro LTDA");
        lojaBtoC2.setCnpj("50.935.855/0001-82");
        lojaBtoC2.setTipoFilial(EnumTipoFilial.LOJA_PF);
        lojaBtoC2.setEndereco("Av. Barata Ribeiro, 199, Rio de Janeiro - RJ");
        Fservice.save(lojaBtoC2);
        
        //Criando Filial - 04
        Filial lojaBtoB = new Filial();
        lojaBtoB.setRazaoSocial("Loja To Loja LTDA");
        lojaBtoB.setCnpj("82.602.541/0001-54");
        lojaBtoB.setTipoFilial(EnumTipoFilial.LOJA_PJ);
        lojaBtoB.setEndereco("Av. Barata Ribeiro, 124, Rio de Janeiro - RJ");
        Fservice.save(lojaBtoB);
        
        List<Filial> filiais = new ArrayList<Filial>();
        filiais.addAll(Fservice.findAll());
        
        //Criando Estoques
        for(int i = 1; i < 97; i++) {
    		Estoque estoque = new Estoque();
    		estoque.setId(Long.parseLong(""+i));
    		estoque.setQtd(rand.nextInt(100));
    		if(i < 25) {
    			estoque.setProduto(produtos.get(i-1));
        		estoque.setFilial(filiais.get(0));
    		}else if(i >=25 && i < 49 ) {
    			estoque.setProduto(produtos.get(i-25));
        		estoque.setFilial(filiais.get(1));
    		}else if(i >= 48 && i < 73) {
    			estoque.setProduto(produtos.get(i-49));
        		estoque.setFilial(filiais.get(2));
    		}else {
    			estoque.setProduto(produtos.get(i-73));
        		estoque.setFilial(filiais.get(3));
    		}
    		service.save(estoque);
    	}
	}
}
