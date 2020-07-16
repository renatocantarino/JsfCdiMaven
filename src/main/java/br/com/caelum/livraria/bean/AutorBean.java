package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorDao  _autorRepository;

	private Autor autor = new Autor();

	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

	@Transacional
	public String gravar() {

		this._autorRepository.adiciona(this.autor);
		this.autor = new Autor();

		return "/livros/lista?faces-redirect=true";
	}

	public void loadById() {
		this.autor = this._autorRepository.buscaPorId(autorId);

	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer AutorId) {
		autorId = AutorId;
	}

}
