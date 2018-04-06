package com.erealty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.erealty.dao.DAOManager;
import com.erealty.services.ApplicationsService;

/**
 * Servlet implementation class SpecialitiesServlet
 */
@WebServlet("/SpecialitiesServlet")
public class SpecialitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SpecialitiesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role != null && role.equals("admin")) {
			ApplicationsService applicationsService = new ApplicationsService(DAOManager.getInstance());
			Integer notActiveCount = applicationsService.getNotActiveApplicationsCount();
			request.setAttribute("notActiveApplications", notActiveCount);
		}
		request.getRequestDispatcher("/pages/specialities.jsp").forward(request, response);
	}
}
