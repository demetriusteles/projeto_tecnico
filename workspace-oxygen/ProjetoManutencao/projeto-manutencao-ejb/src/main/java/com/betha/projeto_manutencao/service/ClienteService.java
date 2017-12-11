package com.betha.projeto_manutencao.service;

import java.util.List;

import com.betha.projeto_manutencao.model.dto.ClienteResponseDTO;

public interface ClienteService {

	String create(ClienteResponseDTO clienteResponse) throws Exception;
	
	ClienteResponseDTO findById(Integer id) throws Exception;
	
	List<ClienteResponseDTO> getClientes(String nome);
}
