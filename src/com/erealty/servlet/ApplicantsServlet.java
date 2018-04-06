package com.erealty.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.erealty.dao.DAOManager;
import com.erealty.model.Applicant;
import com.erealty.model.Speciality;
import com.erealty.services.SpecialityService;

public class ApplicantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicantsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String specID = request.getParameter("specID");
		HttpSession session = request.getSession();
		if(specID != null){
			session.setAttribute("specID", specID);	
		}
		List<Applicant> applicants = new SpecialityService(DAOManager.getInstance())
				.getApplicantsBySpecialityCode((String)session.getAttribute("specID"));
		request.setAttribute("applicantsList", applicants);
		Speciality speciality = new SpecialityService(DAOManager.getInstance())
				.getSpecialityByCode((String)session.getAttribute("specID"));
		request.setAttribute("speciality", speciality);
		request.getRequestDispatcher("/pages/applications.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}