package com.betha.projeto_manutencao.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.betha.projeto_manutencao.model.dto.ClienteResponseDTO;
import com.betha.projeto_manutencao.model.entity.Clientes;
import com.betha.projeto_manutencao.persistence.dao.ClienteDao;
import com.betha.projeto_manutencao.service.ClienteService;

public class ClienteServiceImpl implements ClienteService {
	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

//	@Inject
//	private CrudDao<Clientes> dao;
	
	@Inject
	private ClienteDao dao;

	@Override
	public List<ClienteResponseDTO> getClientes(String nome) {
		List<Clientes> list = dao.find(nome);
		return list.stream()
			.map(cliente -> new ClienteResponseDTO(cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail()))
			.collect(Collectors.toList());
	}

	@Override
	public String create(ClienteResponseDTO dto) throws Exception {
		Clientes cliente = resolveCliente(dto);
		if (null != cliente.getId()) {
			logger.log(Level.WARNING, "Cliente " + cliente.getNome() + " já está cadastrado");
			throw new Exception("Cliente já cadastrado.");
		}
		dao.create(cliente);
		return "OK";
	}
	
	Clientes resolveCliente(final ClienteResponseDTO dto) {
		final Clientes cliente = buscaClientes(dto.getNome());
		return cliente == null ? criaCliente(dto) : cliente;
	}
	
	Clientes buscaClientes(final String clientes) {
		try {
			return dao.findByName(clientes);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	Clientes criaCliente(final ClienteResponseDTO dto) {
		final Clientes cliente = new Clientes();
		cliente.setId(dto.getId());
		cliente.setNome(dto.getNome()); 
		cliente.setEndereco(dto.getEndereco());
		cliente.setTelefone(dto.getTelefone());
		cliente.setEmail(dto.getEmail());
		return cliente;
	}

	@Override
	public ClienteResponseDTO findById(Integer id) throws Exception {
		try {
			Clientes cliente = dao.findById(id);
			return new ClienteResponseDTO(cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail());
		} catch (NoResultException e) {
			logger.severe(e.getMessage());
			throw new Exception(id + " não encontrado");
		}
	}
	
}
