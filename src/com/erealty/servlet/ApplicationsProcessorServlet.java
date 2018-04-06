package com.erealty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erealty.dao.DAOManager;
import com.erealty.services.ApplicationsService;

public class ApplicationsProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplicationsProcessorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("applicationid");
		String action = request.getParameter("action");
		if (action != null && id != null) {
			if (action.equals("confirm")) {
				new ApplicationsService(DAOManager.getInstance()).activateApplicationByID(Integer.valueOf(id));
			}
			if (action.equals("deny")) {
				new ApplicationsService(DAOManager.getInstance()).deleteApplicationByID(Integer.valueOf(id));
			}
		}
		response.sendRedirect("specialities");
	}

}
