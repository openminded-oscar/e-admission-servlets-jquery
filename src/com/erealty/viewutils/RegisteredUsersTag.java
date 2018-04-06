package com.erealty.viewutils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;
import com.erealty.services.AbiturientService;

public class RegisteredUsersTag extends SimpleTagSupport {
	public RegisteredUsersTag() {
	}

	String getMsgLocale(String key) {
		return LocaleSupport.getLocalizedMessage((PageContext) getJspContext(), key, "com.erealty.i18n.inscriptions");
	}

	@Override
	public void doTag() throws JspException, IOException {
		AbiturientService abiturientService = new AbiturientService(DAOManager.getInstance());
		try {
			List<Abiturient> abiturients = abiturientService.selectAllAbiturients();
			JspWriter jspWriter = getJspContext().getOut();
			jspWriter.write("<table border=\"1\"><thead>");
			jspWriter.write("<caption>" + getMsgLocale("userlist.header") + "</caption>");
			jspWriter.write("<tr><th>ID</th><th>" + getMsgLocale("general.form.login") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("general.form.password") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("general.form.fname") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("general.form.lname") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("general.form.mname") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("general.form.photo") + "</th>");
			jspWriter.write("</thead><tbody>");
			for (Abiturient abiturient : abiturients) {
				jspWriter.write("<tr><td>" + abiturient.getID() + "</td><td>" + abiturient.getLogin() + "</td><td>"
						+ abiturient.getPassword() + "</td><td>" + abiturient.getfName() + "</td>" + "<td>" + abiturient.getlName()
						+ "</td><td>" + abiturient.getmName() + "</td>" + "<td>"
						+ (abiturient.getPhotoPath() == null ? "No Image Was Uploaded"
								: "<img src=\""+abiturient.getPhotoPath()+"\" alt=\"picture path is not valid\"height=\"150\" width=\"150\" />")
						+ "</td>" + "</tr>");
			}
			jspWriter.write("<tbody>");
			jspWriter.write("</table>");
		} catch (SQLException e) {
			System.out.println(e + "Error while getting abiturients from db");
		}
	}
}
