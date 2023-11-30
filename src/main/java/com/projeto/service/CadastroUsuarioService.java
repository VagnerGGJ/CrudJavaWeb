package com.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projeto.model.Usuario;
import com.projeto.repository.Usuarios;
import com.projeto.util.Transacional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	@Transacional
	public void excluir(Usuario usuario) {
		usuarios.remover(usuario);
	}
	
}
