package com.betha.projeto_manutencao.persistence.dao;

import java.util.List;

import com.betha.projeto_manutencao.model.entity.Pedidos;
import com.betha.projeto_manutencao.persistence.dao.CrudDao;

public interface PedidoDao extends CrudDao<Pedidos> {

	Pedidos findById(Integer id);

	List<Pedidos> findByStatus(String status);
	
	List<Pedidos> findByClientes(String nome);
	
}