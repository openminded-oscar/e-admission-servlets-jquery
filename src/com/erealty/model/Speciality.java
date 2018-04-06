package com.erealty.model;

import com.erealty.dbutils.ColumnReflection;

public class Speciality {
	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "code")
	private String code;
	@ColumnReflection(columnName = "name")
	private String name;
	@ColumnReflection(columnName = "descriptionUK")
	private String descriptionUK;
	@ColumnReflection(columnName = "descriptionEN")
	private String descriptionEN;
	@ColumnReflection(columnName = "facultyID")
	private Integer facultyID;
	@ColumnReflection(columnName = "budgetLimit")
	private Integer budgetLimit;
	@ColumnReflection(columnName = "commerceLimit")
	private Integer commerceLimit;
	@ColumnReflection(columnName = "annualPayment")
	private Integer annualPayment;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getFacultyID() {
		return facultyID;
	}

	public void setFacultyID(Integer facultyID) {
		this.facultyID = facultyID;
	}

	public Integer getBudgetLimit() {
		return budgetLimit;
	}

	public void setBudgetLimit(Integer budgetLimit) {
		this.budgetLimit = budgetLimit;
	}

	public Integer getCommerceLimit() {
		return commerceLimit;
	}

	public void setCommerceLimit(Integer commerceLimit) {
		this.commerceLimit = commerceLimit;
	}

	public Integer getAnnualPayment() {
		return annualPayment;
	}

	public void setAnnualPayment(Integer annualPayment) {
		this.annualPayment = annualPayment;
	}

	public Speciality() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Speciality [ID=" + ID + ", code=" + code + ", name=" + name + ", descriptionUK=" + descriptionUK
				+ ", descriptionEN=" + descriptionEN + ", facultyID=" + facultyID + ", budgetLimit=" + budgetLimit
				+ ", commerceLimit=" + commerceLimit + ", annualPayment=" + annualPayment + "]";
	}	
}
