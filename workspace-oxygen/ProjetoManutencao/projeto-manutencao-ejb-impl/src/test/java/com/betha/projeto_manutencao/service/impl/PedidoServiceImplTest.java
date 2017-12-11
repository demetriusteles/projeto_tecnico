package com.betha.projeto_manutencao.service.impl;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.betha.projeto_manutencao.model.dto.ClienteResponseDTO;
import com.betha.projeto_manutencao.model.dto.PedidoResponseDTO;
import com.betha.projeto_manutencao.persistence.dao.ClienteDao;
import com.betha.projeto_manutencao.persistence.dao.PedidoDao;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceImplTest extends PedidoServiceImpl {

	
	private static final String ENTRADA = "Entrada";
	private static final String AGUARDANDO_CONFIRMACAO = "Aguardando confirmação";
	@Mock
	private PedidoDao dao;
	@Mock
	private ClienteDao daoCliente;
	@Spy
	@InjectMocks
	private PedidoServiceImplTest service;
	private PedidoResponseDTO registro;
	private ClienteResponseDTO clienteRegistro;
	@Spy
	@InjectMocks
	private ClienteServiceImplTest serviceCliente;

	@Before
	public void setUp() {
		clienteRegistro = new ClienteResponseDTO("frederico", "Rua xyz, 123", "(99)9999-9999","www@www.com");
		registro = new PedidoResponseDTO("xyz", "www", "Descricao", 1, Calendar.getInstance(), "hash", ENTRADA);
	}
	
	@Test
	public void deveCriarNovoPedido() throws Exception {
		serviceCliente.create(clienteRegistro);
		Mockito.verify(daoCliente).findByName(clienteRegistro.getNome());
		service.create(registro);
		Mockito.verify(dao).findByStatus(registro.getStatus());
	}
	
}