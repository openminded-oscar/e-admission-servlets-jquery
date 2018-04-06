package com.erealty.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;
import com.erealty.services.AbiturientService;

/**
 * Servlet implementation class AllUsersServlet
 */
@WebServlet("/AllUsersServlet")
public class AllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AllUsersServlet.class);
       
    public AllUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		AbiturientService userService = new AbiturientService(DAOManager.getInstance());
		try {
			List<Abiturient> users=userService.selectAllAbiturients();
			//response.getWriter().append("Success in new tab"+users.size()+users.toString());
			request.setAttribute("usersList", users);
			getServletConfig().getServletContext().getRequestDispatcher("/pages/users.jsp").forward(request,response);
			LOGGER.info("got info about all users");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// logging example
		// log("User="+user+"::password="+pwd);
		// if(userID.equals(user) && password.equals(pwd)){
		// response.sendRedirect("LoginSuccess.jsp");
		// }else{
		// RequestDispatcher rd =
		// getServletContext().getRequestDispatcher("/login.html");
		// PrintWriter out= response.getWriter();
		// out.println("<font color=red>Either user name or password is
		// wrong.</font>");
		// rd.include(request, response);
		// }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
