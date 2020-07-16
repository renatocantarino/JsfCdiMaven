package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.Log;
import br.com.caelum.livraria.util.SessionManager;
import br.com.caelum.livraria.util.Transacional;
import sun.security.validator.ValidatorException;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LivroDao _livroDao;

	@Inject
	private AutorDao _autorDao;

	@Inject
	private FacesContext ctx;

	private Livro livro = new Livro();

	private Integer autorId;

	private List<Livro> livros;

	@Transacional
	public String gravar() {
		temAutor();
		this._livroDao.adiciona(this.livro);
		this.livros = this._livroDao.listaTodos();

		return "/livros/lista?faces-redirect=true";

	}

	@PostConstruct
	public void Load() {
		if (SessionManager.getSession("livroEdicao") == null)
			return;

		this.livro = (Livro) SessionManager.getSession("livroEdicao");
		SessionManager.clearSession("livroEdicao");

	}

	@Transacional
	public void remover(Livro livro) {

		this._livroDao.remove(livro);
	}

	@Transacional
	public String editar(Livro livro) {

		SessionManager.setSession("livroEdicao", livro);
		return "/livros/add?faces-redirect=true";
	}

	private void temAutor() {
		if (livro.getAutores().isEmpty())
			ctx.addMessage("autor", new FacesMessage("Informe o autor"));
	}

	@Log
	public List<Livro> getAll() {

		if (this.livros == null)
			this.livros = this._livroDao.listaTodos();

		return livros;
	}

	public void vincularAutor() {
		Autor autor = this._autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);

	}

	public void comecaComDigitoUm(FacesContext facesContext, UIComponent uiComponent, Object value)
			throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1"))
			throw new ValidatorException(new FacesMessage("Erro"));

	}

	public String formAutor() {
		return "/autores/add?faces-redirect=true";
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return this._autorDao.todos();
	}

}
