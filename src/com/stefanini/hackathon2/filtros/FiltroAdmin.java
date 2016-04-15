package com.stefanini.hackathon2.filtros;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stefanini.hackathon2.managed.beans.SessaoManagedBean;

@WebFilter(filterName = "FiltroAdmin", description = "Filtro dedicado ao controle do admin e seus respectivos acessos", urlPatterns = {
		"/paginas/emprestimo.xhtml, /paginas/pessoa.xhtml, /paginas/funcionario.xhtml, /paginas/livro.xhtml,"
				+ "/paginas/livro_listaSelection.xhtml, paginas/login.xhtml" })
public class FiltroAdmin implements Filter {

	@Inject
	private SessaoManagedBean session;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		session = (SessaoManagedBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		
		if (session == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/paginas/principal.xhtml");
		} else if (session.getLogin().getLogado()) {
			if (session.getLogin().getAdmin() == true) {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}

}