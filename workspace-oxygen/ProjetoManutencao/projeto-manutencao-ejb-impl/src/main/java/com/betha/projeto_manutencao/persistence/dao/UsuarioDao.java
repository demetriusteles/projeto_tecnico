package com.betha.projeto_manutencao.persistence.dao;

import com.betha.projeto_manutencao.model.entity.Usuarios;
import com.betha.projeto_manutencao.persistence.dao.CrudDao;

public interface UsuarioDao extends CrudDao<Usuarios> {

	Usuarios findById(Integer id);

	Usuarios findByName(String name);
	
	Usuarios findBySetor(String setor);
	
	Usuarios findByUsuario(String usuario);

	Usuarios findByToken(String usuario, String token);
}
