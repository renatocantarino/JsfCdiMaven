package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

public class VendasDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager _entityManager;

	@Inject
	private LivroDao _livroDao;

	private DAO<Venda> _baseDao;

	@PostConstruct
	void init() {
		this._baseDao = new DAO<Venda>(this._entityManager, Venda.class);
	}

	public List<Venda> todas() {

		List<Livro> _livros = this._livroDao.listaTodos();
		List<Venda> _vendas = new ArrayList<Venda>();

		for (Livro livro : _livros) {
			_vendas.add(new Venda(livro, getRandomNumberInRange(1, 10)));
		}

		return _vendas;
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
