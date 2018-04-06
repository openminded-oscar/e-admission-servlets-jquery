package com.erealty.servlet;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.erealty.dao.DAOManager;
import com.erealty.services.SubjectService;


public class GetSubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSubjectsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	ResourceBundle labels = ResourceBundle.getBundle("com.erealty.i18n.inscriptions");
	private String getMsgLocale(String key) {
		return labels.getString(key);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String abiturientLogin = request.getParameter("abiturient");

        Set<String> subjects = new SubjectService(DAOManager.getInstance()).selectSubjectsByAbiturientLogin(abiturientLogin);
        String options = "<option value=\"\" disabled selected hidden>"+getMsgLocale("subject.choose")+"</option>";
        for(String s:subjects){
        	options+="<option value=\""+s+"\">" + getMsgLocale("subject."+s)+"</option>\n";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("json", options);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
