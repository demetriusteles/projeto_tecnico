package com.betha.projeto_manutencao.service;

import javax.security.auth.login.AccountException;

import com.betha.projeto_manutencao.model.dto.UsuarioResponseDTO;

public interface AccountService {
	UsuarioResponseDTO login(String authorization) throws AccountException;
	UsuarioResponseDTO findByToken(String usuario, String token) throws AccountException;
	String create(UsuarioResponseDTO usuarioResponse) throws AccountException;
}
