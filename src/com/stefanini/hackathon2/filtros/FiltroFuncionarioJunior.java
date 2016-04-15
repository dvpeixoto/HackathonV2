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

@WebFilter(filterName = "FiltroFuncionarioJunior", description = "Filtro dedicado ao controle dos funcionário juniors e seus respectivos acessos", urlPatterns = {
		"/livro.xhtml" })
public class FiltroFuncionarioJunior implements Filter {

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
		} else if (session.getLogado()) {
			if (session.getLivro() == true && session.getEmprestimo() == false && session.getPessoa() == false
					&& session.getAdmin() == false) {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}

}