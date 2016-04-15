package com.stefanini.hackathon2.filtros;

import java.io.IOException;

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

import com.stefanini.hackathon2.entidades.Login;

@WebFilter(filterName = "FiltroFuncionarioSenior", description = "Filtro dedicado ao controle dos funcionário seniors e seus respectivos acessos", urlPatterns = {
		"/paginas/livro.xhtml, /paginas/livro_listaSelection.xhtml, /paginas/emprestimo.xhtml"})
public class FiltroFuncionarioSenior implements Filter {

	@Inject
	private Login session;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (session == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/paginas/principal.xhtml");
		} else if (session.getEmprestimo() == true && session.getLivro() == true) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/paginas/principal.xhtml");
		}
	}

	@Override
	public void destroy() {
	}

}