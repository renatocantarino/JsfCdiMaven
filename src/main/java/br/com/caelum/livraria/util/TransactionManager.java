package br.com.caelum.livraria.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class TransactionManager implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager _entityManager;

	@AroundInvoke
	public Object Start(InvocationContext invocationContext) throws Exception {
		_entityManager.getTransaction().begin();

		Object result = invocationContext.proceed();

		_entityManager.getTransaction().commit();

		return result;

	}

}
