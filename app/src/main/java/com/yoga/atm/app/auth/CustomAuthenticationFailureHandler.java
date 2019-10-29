package com.yoga.atm.app.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private Environment env;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String accountNumber = request.getParameter("accountNumber");
		String pin = request.getParameter("pin");
		String message = "";

		if (accountNumber.length() != 6) {
			message += env.getProperty("app.accountnumber.size");
		}
		if (!accountNumber.matches("[0-9]+")) {
			message += env.getProperty("app.accountnumber.number");
		}
		if (pin.length() != 6) {
			message += env.getProperty("app.pin.size");
		}
		if (!pin.matches("[0-9]+")) {
			message += env.getProperty("app.pin.number");
		}
		message += env.getProperty("app.login.invalid");
		request.getSession().setAttribute("message", message);
		redirectStrategy.sendRedirect(request, response, "/");
	}

}
