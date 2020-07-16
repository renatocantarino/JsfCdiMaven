package br.com.caelum.livraria.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Log
@Interceptor
public class LogInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager _entityManager;

	@AroundInvoke
	public Object Start(InvocationContext invocationContext) throws Exception {

		long inicio = System.currentTimeMillis();

		String metodo = invocationContext.getMethod().getName();

		Object result = invocationContext.proceed();

		long fim = System.currentTimeMillis();

		long resultado = fim - inicio;

		System.out.println("Método executado: " + metodo + " Tempo execução: " + resultado);

		return result;

	}

}
