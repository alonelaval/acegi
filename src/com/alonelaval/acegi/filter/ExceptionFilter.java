package com.alonelaval.acegi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 
 * @author huawei
 * @createDate 2009-6-20 下午02:46:38	
 */
public class ExceptionFilter implements Filter {
	protected static final Log logger = LogFactory
	.getLog(ExceptionFilter.class);

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 
		logger.info(response);
		//logger.info(request.)
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
