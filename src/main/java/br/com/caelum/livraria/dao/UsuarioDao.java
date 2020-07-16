package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Usuario;


public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager _entityManager;

	private DAO<Usuario> _baseDao;

	@PostConstruct
	void init() {
		this._baseDao = new DAO<Usuario>(this._entityManager, Usuario.class);
	}
	
	
	public boolean existe(Usuario usuario) {

		Usuario singleResult = _entityManager
				.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class)
				.setParameter("pEmail", usuario.getEmail())
				.setParameter("pSenha", usuario.getSenha())
				.getResultList()
				.stream()
				.findFirst().orElse(null);

		return singleResult != null;

	}

}
