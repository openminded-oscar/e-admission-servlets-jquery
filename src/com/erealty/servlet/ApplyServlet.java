package com.erealty.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.erealty.dao.DAOManager;
import com.erealty.model.Applicant;
import com.erealty.model.Speciality;
import com.erealty.services.ApplicationsService;
import com.erealty.services.ApplyService;
import com.erealty.services.SpecialityService;

public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ApplyServlet.class);

	public ApplyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			ApplyService service = new ApplyService(DAOManager.getInstance());
			String login = (String) session.getAttribute("curUser");
			Boolean added = service.apply((String) session.getAttribute("curUser"),
					(String) session.getAttribute("specID"));
			if (added!=null&&added.equals(true)) {
				session.setAttribute("applyStatus", "applied");
				request.setAttribute("justApplied", "yes");
				Map<String,Boolean> specialities = new ApplicationsService(DAOManager.getInstance()).getSpecialitiesToWhichApplied(login);
				session.setAttribute("appliedSpecialities", specialities);				
			}
		} catch (SQLException e) {
			LOGGER.error("error occured when getting list of applications");
			e.printStackTrace();
		}
		List<Applicant> applicants = new SpecialityService(DAOManager.getInstance())
				.getApplicantsBySpecialityCode((String)request.getSession().getAttribute("specID"));
		request.setAttribute("applicantsList", applicants);
		Speciality speciality = new SpecialityService(DAOManager.getInstance())
				.getSpecialityByCode((String)request.getSession().getAttribute("specID"));
		request.setAttribute("speciality", speciality);
		request.getRequestDispatcher("/pages/applications.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
