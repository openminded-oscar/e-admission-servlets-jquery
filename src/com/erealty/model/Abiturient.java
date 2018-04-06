package com.erealty.model;

import java.sql.Date;

import com.erealty.dbutils.ColumnReflection;

public class Abiturient {
	public Abiturient() {
		super();
	}

	public Abiturient(String login, String password, String photoPath, String fName, String lName, String mName,
			Date DOB, String telNumber, String eMail, String docPhoto1Path, String docPhoto2Path, Integer ZNONumber,
			Integer gradCertNumber) {
		super();
		this.login = login;
		this.password = password;
		this.photoPath = photoPath;
		this.fName = fName;
		this.lName = lName;
		this.mName = mName;
		this.DOB = DOB;
		this.telNumber = telNumber;
		this.eMail = eMail;
		this.docPhoto1Path = docPhoto1Path;
		this.docPhoto2Path = docPhoto2Path;
		this.ZNONumber = ZNONumber;
		this.gradCertNumber = gradCertNumber;
	}

	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "login")
	private String login;
	@ColumnReflection(columnName = "password")
	private String password;
	@ColumnReflection(columnName = "photoPath")
	private String photoPath;
	@ColumnReflection(columnName = "fName")
	private String fName;
	@ColumnReflection(columnName = "lName")
	private String lName;
	@ColumnReflection(columnName = "mName")
	private String mName;
	@ColumnReflection(columnName = "DOB")
	private Date DOB;
	@ColumnReflection(columnName = "telNumber")
	private String telNumber;
	@ColumnReflection(columnName = "eMail")
	private String eMail;
	@ColumnReflection(columnName = "docPhoto1Path")
	private String docPhoto1Path;
	@ColumnReflection(columnName = "docPhoto2Path")
	private String docPhoto2Path;
	@ColumnReflection(columnName = "ZNONumber")
	private Integer ZNONumber;
	@ColumnReflection(columnName = "gradCertNumber")
	private Integer gradCertNumber;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getDocPhoto1Path() {
		return docPhoto1Path;
	}

	public void setDocPhoto1Path(String docPhoto1Path) {
		this.docPhoto1Path = docPhoto1Path;
	}

	public String getDocPhoto2Path() {
		return docPhoto2Path;
	}

	public void setDocPhoto2Path(String docPhoto2Path) {
		this.docPhoto2Path = docPhoto2Path;
	}

	public Integer getZNONumber() {
		return ZNONumber;
	}

	public void setZNONumber(Integer zNONumber) {
		ZNONumber = zNONumber;
	}

	public Integer getGradCertNumber() {
		return gradCertNumber;
	}

	public void setGradCertNumber(Integer gradCertNumber) {
		this.gradCertNumber = gradCertNumber;
	}

	@Override
	public String toString() {
		return "Abiturient [ID=" + ID + ", login=" + login + ", password=" + password + ", photoPath=" + photoPath
				+ ", fName=" + fName + ", lName=" + lName + ", mName=" + mName + ", DOB=" + DOB + ", telNumber="
				+ telNumber + ", eMail=" + eMail + ", docPhoto1Path=" + docPhoto1Path + ", docPhoto2Path="
				+ docPhoto2Path + ", ZNONumber=" + ZNONumber + ", gradCertNumber=" + gradCertNumber + "]";
	}
	
}