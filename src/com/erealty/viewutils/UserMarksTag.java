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
import com.erealty.model.Mark;
import com.erealty.services.MarkService;

public class UserMarksTag extends SimpleTagSupport {
	public UserMarksTag() {
	}

	private String login;

	public void setLogin(String login) {
		this.login = login;
	}

	String getMsgLocale(String key) {
		return LocaleSupport.getLocalizedMessage((PageContext) getJspContext(), key, "com.erealty.i18n.inscriptions");
	}

	@Override
	public void doTag() throws JspException, IOException {
		MarkService markService = new MarkService(DAOManager.getInstance());
		try {
			JspWriter jspWriter = getJspContext().getOut();
			jspWriter.write("<table class=\"table table-striped\" border=\"1\"><thead>");
			jspWriter.write("<tr><th class=\"col-sm-3\">" + getMsgLocale("mark.number") + "</th>");
			jspWriter.write("<th class=\"col-sm-6\">" + getMsgLocale("mark.subject") + "</th>");
			jspWriter.write("<th class=\"col-sm-3\">" + getMsgLocale("mark.points") + "</th>");
			jspWriter.write("</thead><tbody>");
			List<Mark> marks = markService.getAllMarksByAbiturientLogin(login);
			for (int i = 0; i < marks.size(); ++i) {
				Mark mark = marks.get(i);
				jspWriter.write("<tr><td class=\"col-sm-3\">" + (i + 1) + "</td><td class=\"col-sm-6\">"
						+ getMsgLocale("subject."+mark.getSubj()) + "</td>" + "<td class=\"col-sm-3\">" + mark.getPoints() + "</td></tr>");
			}
			jspWriter.write("<tr><td class=\"col-sm-3\"></td><td class=\"col-sm-3\">"
					+ "<button type=\"button\" class=\"btn btn-default btn-lg btn-danger\" id=\"myBtn\">+</button>"
					+ "</td><td class=\"col-sm-3\"></td></tr>");
			jspWriter.write("</tbody>");
			jspWriter.write("</table>");
			// <a href="somepage.php?field1=foo&field2=bar"
			// onclick="document.getElementById('form1').submit(); return
			// false;">Hyperlink</a>

		} catch (SQLException e) {
			System.out.println(e + "Error while getting specialities from db");
		}
	}
}
