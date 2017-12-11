package com.betha.projeto_manutencao.persistence.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.betha.projeto_manutencao.model.entity.Pedidos;
import com.betha.projeto_manutencao.persistence.config.DataSourceQualifier;
import com.betha.projeto_manutencao.persistence.dao.PedidoDao;

public class PedidoDaoJpa extends AbstractCrudDaoJpa<Pedidos> implements PedidoDao {

	@Inject
	@DataSourceQualifier
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<Pedidos> getModelClass() {
		return Pedidos.class;
	}

	@Override
	public Pedidos findById(Integer id) {
		String sql = "SELECT p FROM pedidos p WHERE (m.id = :id)";

		final TypedQuery<Pedidos> query = getEntityManager().createQuery(sql, Pedidos.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<Pedidos> findByStatus(String status) {
		String sql = "SELECT p FROM pedidos p  WHERE (m.status = :status)";

		final TypedQuery<Pedidos> query = getEntityManager().createQuery(sql, Pedidos.class);
		query.setParameter("tipoMaquina", status);
		return query.getResultList();
	}

	@Override
	public List<Pedidos> findByClientes(String nome) {
		String sql = "SELECT p FROM pedidos p join fetch p.cliente c"
				+ " WHERE (c.nome = :nome)";

		final TypedQuery<Pedidos> query = getEntityManager().createQuery(sql, Pedidos.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

}
