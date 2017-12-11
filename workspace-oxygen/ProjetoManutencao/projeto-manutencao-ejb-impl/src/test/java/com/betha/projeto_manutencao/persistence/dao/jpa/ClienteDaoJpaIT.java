package com.betha.projeto_manutencao.persistence.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.betha.projeto_manutencao.it.JpaBasedDBTestCase;
import com.betha.projeto_manutencao.it.WeldJUnit4Runner;
import com.betha.projeto_manutencao.model.entity.Clientes;
import com.betha.projeto_manutencao.persistence.dao.ClienteDao;

@RunWith(WeldJUnit4Runner.class)
public class ClienteDaoJpaIT extends JpaBasedDBTestCase {
	@Inject
	private ClienteDao dao;

	@Override
	protected String getDataSetPath() {
		return "/dataset/Clientes.xml";
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return dao.getEntityManager();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	//@Test
	public void deveBuscarClientes() {
		List<Clientes> clientes = dao.find("");
		Assert.assertNotNull(clientes);
		Assert.assertEquals(2, clientes.size());
	}

	//@Test
	public void deveBuscarClientesPorId() {
		Integer id = 10;
		Clientes clientes = dao.findById(id);
		Assert.assertNotNull(clientes);
		Assert.assertEquals(id, clientes.getId());
	}
	
	//@Test
	public void deveBuscarClientePorNome() {
		String name = "fred fred";
		Clientes cliente = dao.findByName(name);
		Assert.assertNotNull(cliente);
		Assert.assertEquals(name, cliente.getNome());
	}

}
