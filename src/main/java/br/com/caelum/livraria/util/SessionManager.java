package br.com.caelum.livraria.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class SessionManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void setSession(String key, Object entity) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getExternalContext().getSessionMap().put(key, entity);

	}

	public static Object getSession(String key) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		return ctx.getExternalContext().getSessionMap().get(key);
	}

	public static void clearSession(String key) {

		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getExternalContext().getSessionMap().remove(key);
	}

}
