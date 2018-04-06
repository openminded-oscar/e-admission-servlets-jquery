package com.erealty.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.erealty.dao.DAOManager;
import com.erealty.services.ApplicationsService;

/**
 * Servlet Filter implementation class IfAppliedFilter
 */
public class IfAppliedFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public IfAppliedFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		String role = (String) session.getAttribute("role");
		if (role!=null&&role.equals("user")) {
			String specialityCode = request.getParameter("specID"); 
			if(specialityCode==null){
				specialityCode = (String) session.getAttribute("specID");
			}
			String login = (String) session.getAttribute("curUser");
			if (new ApplicationsService(DAOManager.getInstance())
					.getApplicationsBySpecialityAndAbiturient(specialityCode, login) != null){
				session.setAttribute("applyStatus", "applied");
			}
			else{
			session.setAttribute("applyStatus", null);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
