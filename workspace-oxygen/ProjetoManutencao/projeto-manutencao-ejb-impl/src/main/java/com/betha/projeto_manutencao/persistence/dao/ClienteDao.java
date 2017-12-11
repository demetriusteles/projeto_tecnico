package com.betha.projeto_manutencao.persistence.dao;

import com.betha.projeto_manutencao.model.entity.Clientes;

public interface ClienteDao extends CrudDao<Clientes> {

	Clientes findById(Integer id);

	Clientes findByName(String name);

}
