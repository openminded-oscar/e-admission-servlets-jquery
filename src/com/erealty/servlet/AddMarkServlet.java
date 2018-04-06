package com.erealty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;
import com.erealty.model.Mark;
import com.erealty.services.AbiturientService;
import com.erealty.services.MarkService;

public class AddMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println(request.getParameterMap());
		AbiturientService abiturientService = new AbiturientService(DAOManager.getInstance());
		Abiturient abiturient = abiturientService.selectAbiturientByLogin((String) session.getAttribute("curUser"));
		MarkService markService = new MarkService(DAOManager.getInstance());
		Integer abiturID = abiturient.getID();
		String subject = request.getParameter("subject");
		System.out.println(request.getParameterMap());
		Integer points = Integer.valueOf(request.getParameter("gotpoints"));
		System.out.println(abiturID+subject+points);
		markService.addMark(new Mark(abiturID,subject,points));
		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	}

}
