package com.rns.testes.java.dto.mapper;

import org.mapstruct.factory.Mappers;

import com.rns.testes.java.dao.IEstoqueDao;
import com.rns.testes.java.model.Estoque;

public interface EstoqueMapper extends GenericMapper<Estoque, IEstoqueDao> {

	EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class); 
}
