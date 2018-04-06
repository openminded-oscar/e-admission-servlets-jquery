package com.erealty.model;

import com.erealty.dbutils.ColumnReflection;

public class Faculty {
	public Faculty() {
		super();
	}

	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "title")
	private String title;
	@ColumnReflection(columnName = "descriptionUK")
	private String descriptionUK;
	@ColumnReflection(columnName = "descriptionEN")
	private String descriptionEN;
	@ColumnReflection(columnName = "telNumber")
	private String telNumber;
	@ColumnReflection(columnName = "eMail")
	private String eMail;
	@ColumnReflection(columnName = "director")
	private String director;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptionUK() {
		return descriptionUK;
	}

	public void setDescriptionUK(String descriptionUK) {
		this.descriptionUK = descriptionUK;
	}

	public String getDescriptionEN() {
		return descriptionEN;
	}

	public void setDescriptionEN(String descriptionEN) {
		this.descriptionEN = descriptionEN;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
}