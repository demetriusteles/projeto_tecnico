package com.betha.projeto_manutencao.service.impl;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.betha.projeto_manutencao.model.dto.ClienteResponseDTO;
import com.betha.projeto_manutencao.persistence.dao.ClienteDao;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest extends ClienteServiceImpl {


	@Mock
	private ClienteDao dao;
	@Spy
	@InjectMocks
	private ClienteServiceImplTest service;
	private ClienteResponseDTO registro;

	@Before
	public void setUp() {
		registro = new ClienteResponseDTO("frederico", "Rua xyz, 123", "(99)9999-9999","www@www.com");
	}
	
	@Test
	public void deveCriarNovoCliente() throws Exception {
		Mockito.when(dao.findByName(registro.getNome())).thenThrow(new NoResultException());
		service.create(registro);
		Mockito.verify(dao).findByName(registro.getNome());
	}
	
}