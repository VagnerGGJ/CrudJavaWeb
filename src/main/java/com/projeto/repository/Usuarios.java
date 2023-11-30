package com.projeto.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.projeto.model.Usuario;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuarios() {

	}

	public Usuarios(EntityManager manager) {

		this.manager = manager;

	}

	public Usuario porId(int id) {

		return manager.find(Usuario.class, id);

	}

	public List<Usuario> pesquisar(String nome) {
		String jpql = "From Usuario where USU_NOME like :usu_nome";

		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		
		query.setParameter("usu_nome", nome + "%");
		
		return query.getResultList();

	}
	
	public List<Usuario> todas() {
		return manager.createQuery("From Usuario", Usuario.class).getResultList();
	}

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public void remover(Usuario usuario) {

		usuario = porId(usuario.getUSU_CODIGO());
		manager.remove(usuario);

	}
	
	public List<Usuario> listarAtivos() {
		
		String jpql = "From Usuario where usu_ativo = 1";

		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		
		return query.getResultList();
		
	}
	
	public List<Usuario> listarInativos() {
		
		String jpql = "From Usuario where usu_ativo = 0";

		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		
		return query.getResultList();
		
	}

}
