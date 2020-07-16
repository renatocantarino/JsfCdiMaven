package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager _entityManager;
	private DAO<Livro> _baseDao;

	@PostConstruct
	void init() {
		this._baseDao = new DAO<Livro>(this._entityManager, Livro.class);
	}

	public void adiciona(Livro livro) {

		if (livro.getId() == null)
			_baseDao.adiciona(livro);
		else
			atualiza(livro);
	}

	private void atualiza(Livro livro) {
		_baseDao.atualiza(livro);
	}

	public List<Livro> listaTodos() {
		return _baseDao.listaTodos();
	}

	public void remove(Livro livro) {
		_baseDao.remove(livro);
	}

	public Livro buscaPorId(Integer id) {
		return _baseDao.buscaPorId(id);
	}
	
	

}
