package com.example.demo.configs.restsecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RESTCsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {

	protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, javax.servlet.FilterChain filterChain)
			throws ServletException, IOException {
		CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);

		if (token != null) {
			response.setHeader(token.getHeaderName(), token.getToken());
		}

		filterChain.doFilter(request, response);
	}
}
