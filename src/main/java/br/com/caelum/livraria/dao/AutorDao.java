package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;

public class AutorDao implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager _entityManager;
	private DAO<Autor> _baseDao;

	@PostConstruct
	void init() {
		this._baseDao = new DAO<Autor>(this._entityManager, Autor.class);
	}

	public void adiciona(Autor autor) {
		this._baseDao.adiciona(autor);

	}

	public void atualiza(Autor autor) {
		this._baseDao.atualiza(autor);

	}

	public Autor buscaPorId(Integer autorId) {

		return this._baseDao.buscaPorId(autorId);
	}

	public List<Autor> todos() {

		return this._baseDao.listaTodos();
	}

}
