package com.erealty.viewutils;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;
import com.erealty.model.Application;
import com.erealty.model.Mark;
import com.erealty.model.Speciality;
import com.erealty.services.AbiturientService;
import com.erealty.services.ApplicationsService;
import com.erealty.services.MarkService;
import com.erealty.services.SpecialityService;

public class ApplicationsTag extends SimpleTagSupport {
	public ApplicationsTag() {
	}

	String getMsgLocale(String key) {
		return LocaleSupport.getLocalizedMessage((PageContext) getJspContext(), key, "com.erealty.i18n.inscriptions");
	}

	@Override
	public void doTag() throws JspException, IOException {
		ApplicationsService specialityService = new ApplicationsService(DAOManager.getInstance());
		JspWriter jspWriter = getJspContext().getOut();
		jspWriter.write("<table style=\"font-size:0.6em;\"><thead><tr class=\"row\">");
		jspWriter.write("<th class=\"col-sm-2\">" + getMsgLocale("applicant.fullname") + "</th>");
		jspWriter.write("<th class=\"col-sm-2\">"+getMsgLocale("applicant.photo")+"</th>");
		jspWriter.write("<th class=\"col-sm-2\">"+getMsgLocale("applicant.speciality")+"</th>");
		jspWriter.write("<th class=\"col-sm-3\">" + getMsgLocale("applicant.marks") + "</th>");
		jspWriter.write("</thead><tbody>");
		List<Application> applications = specialityService.getNotActiveApplications();
		for (Application application : applications) {

			Speciality speciality = new SpecialityService(DAOManager.getInstance())
					.getSpecialityByID(application.getSpecialityID());
			String specialityName = speciality.getName();
			List<Mark> marks = new MarkService(DAOManager.getInstance()).getAllMarksByApplication(application);
			Abiturient abiturient = new AbiturientService(DAOManager.getInstance())
					.selectAbiturientById(marks.get(0).getAbiturientID());
			String tBody = "<tr id=\""+application.getID()+"\" class=\"row\" style=\"border-bottom:1pt solid black;border-top:1pt solid black;\"><td class=\"col-sm-2\">" + abiturient.getfName() +" "+ abiturient.getlName()
					+ (abiturient.getmName() == null ? "" : " "+abiturient.getmName()) + "</td><td class=\"col-sm-2\">"
					+ (abiturient.getPhotoPath() == null ? "No image uploaded"
							: "<img src=\"" + abiturient.getPhotoPath()
									+ "\" alt=\"picture path is not valid\" height=\"75\" width=\"75\" />")
					+ "</td><td class=\"col-sm-2\">"+specialityName+"</td><td class=\"col-sm-3\">"
					+outputMarks(marks)+ "</td><td class=\"col-sm-1\"><a onclick=\"deleteApplicationConfirm('"+application.getID()+"')\" href=\"#\" "
					+ " type=\"button\" class=\"btn btn-success btn-block\"> "
					+ " <span class=\"glyphicon glyphicon-ok\"></span></a></td><td class=\"col-sm-1\">"
					+ "<a onclick=\"deleteApplicationDeny('"+application.getID()+"')\" href=\"#\" type=\"button\" class=\"btn btn-danger btn-block\" "
					+ "><span	class=\"glyphicon glyphicon-remove\"></span> </a></td></tr>";
			jspWriter.write(tBody);
		}
		jspWriter.write("<tbody>");
		jspWriter.write("</table>");
	}
	
	private String outputMarks(List<Mark> marks){
		String result = getMsgLocale("subject."+marks.get(0).getSubj())+" - "+marks.get(0).getPoints();
		for(int i=1;i<marks.size();++i){
			result+="\n"+getMsgLocale("subject."+marks.get(i).getSubj())+" - "+marks.get(i).getPoints();
		}
		return result;
	}

}
