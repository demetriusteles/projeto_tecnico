package com.betha.projeto_manutencao.persistence.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:luisdemetrius@gmail.com.br">Luis Demetrius Teles</a>
 */

@Default
@ApplicationScoped
public class EntityManagerProviderImpl implements EntityManagerProvider {
	@PersistenceContext(unitName = "projeto-manutencao-pu")
	private EntityManager entityManager;

	@Override
	@Produces
	@RequestScoped
	@DataSourceQualifier
	public EntityManager produce() {
		return this.entityManager;
	}

	@Override
	public void dispose(@Disposes @DataSourceQualifier final EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}
}
