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
import com.erealty.model.Application;
import com.erealty.model.Speciality;
import com.erealty.services.ApplicationsService;
import com.erealty.services.ApplyService;
import com.erealty.services.SpecialityService;

public class SpecialitiesTag extends SimpleTagSupport {
	public SpecialitiesTag() {
	}

	private String lang;

	public void setLang(String lang) {
		this.lang = lang;
	}

	private String abiturient;

	public void setAbiturient(String abiturient) {
		this.abiturient = abiturient;
	}

	private String getMsgLocale(String key) {
		return LocaleSupport.getLocalizedMessage((PageContext) getJspContext(), key, "com.erealty.i18n.inscriptions");
	}

	@Override
	public void doTag() throws JspException, IOException {
		SpecialityService specialityService = new SpecialityService(DAOManager.getInstance());
		try {
			JspWriter jspWriter = getJspContext().getOut();
			jspWriter.write("<table class=\"table table-hover table-bordered\"><thead>");
			jspWriter.write("<tr>" + "<th>" + getMsgLocale("speciality.code") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("speciality.name") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("speciality.description") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("speciality.faculty") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("speciality.places") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("speciality.tuition") + "</th>");
			jspWriter.write("<th>" + getMsgLocale("speciality.applicants") + "</th>");
			jspWriter.write("</thead><tbody>");
			List<Speciality> specialities = specialityService.getAllSpecialities();
			ApplicationsService applicationService = null;
			if ((abiturient != null) && (abiturient.equals("") == false)) {
				applicationService = new ApplicationsService(DAOManager.getInstance());
			}
			for (Speciality speciality : specialities) {
				jspWriter.write("<tr><td>" + speciality.getCode() + "</td><td title=\""
						+ getMsgLocale("applications.view.invitation") + "\"><a href=\"applicants?specID="
						+ speciality.getCode() + "\">" + speciality.getName() + "</a></td><td>"
						+ (lang.equals("uk_UA") ? speciality.getDescriptionUK() : speciality.getDescriptionEN())
						+ "</td><td>" + specialityService.getFacultyNameBySpeciality(speciality) + "</td><td>"
						+ speciality.getBudgetLimit() + "/" + speciality.getCommerceLimit() + "</td><td>"
						+ speciality.getAnnualPayment() + "</td><td>"
						+ specialityService.getApplicationNumberBySpeciality(speciality, true) + "</td>");
				if ((abiturient != null) && (abiturient.equals("") == false)) {
					Application application = applicationService
							.getApplicationsBySpecialityAndAbiturient(speciality.getCode(), abiturient);
					ApplyService applyService = null;
					if (application == null) {
						applyService = new ApplyService(DAOManager.getInstance());
					}
					jspWriter.write("<td>" + (application == null
							? applyService.ifApplyAvailable(abiturient, speciality.getCode()) == true
									? "<span style=\"color:green\" class=\"glyphicon glyphicon-plus-sign\"></span>"
									: "<span style=\"color:red\" class=\"glyphicon glyphicon-ban-circle\"></span>"
							: "<span style=\"color:green\" class=\"glyphicon glyphicon-ok\"></span>") + "</td>");
				}
				jspWriter.write("</tr>");
			}
			jspWriter.write("<tbody>");
			jspWriter.write("</table>");

		} catch (SQLException e) {
			System.out.println(e + "Error while getting specialities from db");
		}
	}
}
