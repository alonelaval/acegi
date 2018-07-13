package com.alonelaval.acegi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.concurrent.SessionInformation;
import org.acegisecurity.concurrent.SessionRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author dev.zhang
 * 
 */
public class ConcurrentSessionFilter implements Filter, InitializingBean {

	// ~ Instance fields
	// ================================================================================================

	private SessionRegistry sessionRegistry;
	private String expiredUrl;

	// ~ Methods
	// ========================================================================================================

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(sessionRegistry, "SessionRegistry required");
		Assert.hasText(expiredUrl, "ExpiredUrl required");
	}

	/**
	 * Does nothing. We use IoC container lifecycle services instead.
	 */
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Assert.isInstanceOf(HttpServletRequest.class, request, "Can only process HttpServletRequest");
		Assert.isInstanceOf(HttpServletResponse.class, response, "Can only process HttpServletResponse");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		if (session != null) {
			SessionInformation info = sessionRegistry.getSessionInformation(session.getId());

			if (info != null) {
				if (info.isExpired()) {
					// Expired - abort processing
					session.invalidate();

					String targetUrl = httpRequest.getContextPath() + expiredUrl;
					httpResponse.sendRedirect(httpResponse.encodeRedirectURL(targetUrl));

					return;
				} else {
					// Non-expired - update last request date/time
					info.refreshLastRequest();
				}
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * Does nothing. We use IoC container lifecycle services instead.
	 * 
	 * @param arg0
	 *            ignored
	 * 
	 * @throws ServletException
	 *             ignored
	 */
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void setExpiredUrl(String expiredUrl) {
		this.expiredUrl = expiredUrl;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
}
