package com.erealty.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import com.erealty.dao.DAOManager;
import com.erealty.dbutils.PasswordEncoder;
import com.erealty.model.Abiturient;
import com.erealty.services.RegistrationService;

/**
 * Servlet implementation class RegisterServlet
 */
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERS_RELATIVE_DIRECTORY = "images" + File.separator + "users";
	//private static final String PHOTOS_DIRECTORY = "D:/Work/IT/epam/webprojdata/img/users";
	
	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		try {
			String login = request.getParameter("login");
			request.setAttribute("login", login);
			String password = request.getParameter("password");
			request.setAttribute("password", password);
			String fname = request.getParameter("fname");
			request.setAttribute("fname", fname);
			String lname = request.getParameter("lname");
			request.setAttribute("lname", lname);
			String mname = request.getParameter("mname");
			request.setAttribute("mname", mname);
			String dobString = request.getParameter("DOB");
			request.setAttribute("DOB", dobString);
			java.util.Date dateTmp;
			dateTmp = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);
			Date DOB = new Date(dateTmp.getTime());
			String phone = request.getParameter("phone");
			request.setAttribute("phone", phone);
			String email = request.getParameter("email");
			request.setAttribute("email", email);
			String zno = request.getParameter("zno");
			request.setAttribute("zno", zno);
			String graduationCertificate = request.getParameter("graduationCertificate");
			request.setAttribute("graduationCertificate", graduationCertificate);
			Part filePart = request.getPart("photo");
			
			String uploadPath = getServletContext().getRealPath("") + USERS_RELATIVE_DIRECTORY;
			
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String fileNameWithExt = login + "." + FilenameUtils.getExtension(filePart.getSubmittedFileName());
			String filePath = uploadPath + File.separator + fileNameWithExt;
//			filePart.write(filePath);
			System.out.println(filePath);
			
			try {
				RegistrationService registrationService=new RegistrationService(DAOManager.getInstance());
				Boolean registerResult = registrationService.register(new Abiturient(login, PasswordEncoder.getMD5(password),
						USERS_RELATIVE_DIRECTORY + File.separator + fileNameWithExt, 
						fname,lname, mname.trim().equals("")?null:mname, DOB, phone,email,null,null,
						Integer.valueOf(zno),Integer.valueOf(graduationCertificate)));
				if (registerResult!=null&&registerResult.booleanValue()) {
					filePart.write(filePath);
					request.setAttribute("addingResult", true);
				} else {
					request.setAttribute("addingResult", false);
				}
				getServletConfig().getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request,
						response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		URL url = IndexServlet.class.getResource("/");
		System.out.println(url.toString());
	}
}