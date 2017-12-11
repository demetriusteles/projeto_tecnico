package com.betha.projeto_manutencao.persistence.dao.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.betha.projeto_manutencao.model.entity.Clientes;
import com.betha.projeto_manutencao.persistence.config.DataSourceQualifier;
import com.betha.projeto_manutencao.persistence.dao.ClienteDao;

public class ClienteDaoJpa extends AbstractCrudDaoJpa<Clientes> implements ClienteDao {

	@Inject
	@DataSourceQualifier
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<Clientes> getModelClass() {
		return Clientes.class;
	}

	@Override
	public Clientes findById(Integer id) {
		String sql = "SELECT c FROM clientes c WHERE (c.id = :id)";

		final TypedQuery<Clientes> query = getEntityManager().createQuery(sql, Clientes.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public Clientes findByName(String name) {
		String sql = "SELECT c FROM clientes c WHERE (c.name = :name)";

		final TypedQuery<Clientes> query = getEntityManager().createQuery(sql, Clientes.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

}