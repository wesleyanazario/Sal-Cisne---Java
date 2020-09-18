package com.rns.testes.java.seeder;

import com.rns.testes.java.enums.EnumTipoFilial;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jms.Session;
import javax.transaction.Transaction;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FilialSeeder {
	

    @Autowired
    IFilialService service;
    
    @Autowired
    IProdutoService Pservice; 

    @EventListener
    public void seedFilial(ContextRefreshedEvent event) {
        try {
            log.info("Criando filiais....");
            criandoFiliais();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    private void criandoFiliais() {
        
		
		
    }
}
