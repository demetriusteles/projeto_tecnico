package com.betha.projeto_manutencao.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

public class DbConnectionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Connection conn = getDbConnection();
		request.setAttribute("dbConnection", conn);
		chain.doFilter(request, response);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	private Connection getDbConnection() throws ServletException {
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/ProjetoManutencaoDS");
			return ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		}
	}
}
