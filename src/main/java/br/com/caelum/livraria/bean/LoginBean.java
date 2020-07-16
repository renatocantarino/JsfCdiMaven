package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.SessionManager;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao _usuarioDao;

	@Inject
	private FacesContext ctx;

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {

		if (this._usuarioDao.existe(this.usuario)) {
			SessionManager.setSession("usuarioLogado", this.usuario);
			return "livros/lista?faces-redirect=true";

		}

		ctx.addMessage("msgs", new FacesMessage("Usuario não encontrado"));
		return "login";
	}

	public String logOut() {

		SessionManager.clearSession("usuarioLogado");
		return "/login?faces-redirect=true";

	}

}
