package com.erealty.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.erealty.dao.DAOManager;
import com.erealty.dbutils.PasswordEncoder;
import com.erealty.model.Abiturient;
import com.erealty.model.Administrator;
import com.erealty.services.ApplicationsService;
import com.erealty.services.LogInService;

/**
 * Servlet implementation class EntranceServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role != null && role.equals("admin")) {
			ApplicationsService applicationsService = new ApplicationsService(DAOManager.getInstance());
			Integer notActiveCount = applicationsService.getNotActiveApplicationsCount();
			request.setAttribute("notActiveApplications", notActiveCount);
			request.getRequestDispatcher("/pages/specialities.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		HttpSession session = request.getSession();
		session.setAttribute("curUser", login);
		request.setAttribute("login", login);
		String password = request.getParameter("password");
		Administrator admin;
		Abiturient abitur;
		admin = new LogInService(DAOManager.getInstance()).selectAdminByLogin(login);
		if (admin != null && admin.getPassword().equals(PasswordEncoder.getMD5(password))) {
			session = request.getSession();
			session.setAttribute("role", "admin");
			ApplicationsService applicationsService = new ApplicationsService(DAOManager.getInstance());
			Integer notActiveCount = applicationsService.getNotActiveApplicationsCount();
			request.setAttribute("notActiveApplications", notActiveCount);
			request.getRequestDispatcher("/pages/specialities.jsp").forward(request, response);
		} else {
			abitur = new LogInService(DAOManager.getInstance()).selectAbiturientByLogin(login);
			System.out.println(abitur);
			if (abitur != null && abitur.getPassword().equals(PasswordEncoder.getMD5(password))) {
				session = request.getSession();
				session.setAttribute("role", "user");
				session.setAttribute("abiturient", abitur);
				Map<String, Boolean> specialities = new ApplicationsService(DAOManager.getInstance())
						.getSpecialitiesToWhichApplied(login);
				session.setAttribute("appliedSpecialities", specialities);
			} else {
				request.setAttribute("error", "true");
				request.setAttribute("error_message", "auth.error");
			}
			request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
		}
	}
}
