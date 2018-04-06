package com.erealty.model;

import com.erealty.dbutils.ColumnReflection;

public class Application {
	public Application() {
		super();
	}

	public Application(Integer specialityID, Integer mark1id, Integer mark2id, Integer mark3id,
			Integer graduationMarkID, Boolean active) {
		super();
		this.specialityID = specialityID;
		mark1ID = mark1id;
		mark2ID = mark2id;
		mark3ID = mark3id;
		this.graduationMarkID = graduationMarkID;
		this.active = active;
	}

	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "specialityID")
	private Integer specialityID;
	@ColumnReflection(columnName = "mark1ID")
	private Integer mark1ID;
	@ColumnReflection(columnName = "mark2ID")
	private Integer mark2ID;
	@ColumnReflection(columnName = "mark3ID")
	private Integer mark3ID;
	@ColumnReflection(columnName = "graduationMarkID")
	private Integer graduationMarkID;
	@ColumnReflection(columnName = "active")
	private Boolean active;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getSpecialityID() {
		return specialityID;
	}

	public void setSpecialityID(Integer specialityID) {
		this.specialityID = specialityID;
	}

	public Integer getMark1ID() {
		return mark1ID;
	}

	public void setMark1ID(Integer mark1id) {
		mark1ID = mark1id;
	}

	public Integer getMark2ID() {
		return mark2ID;
	}

	public void setMark2ID(Integer mark2id) {
		mark2ID = mark2id;
	}

	public Integer getMark3ID() {
		return mark3ID;
	}

	public void setMark3ID(Integer mark3id) {
		mark3ID = mark3id;
	}

	public Integer getGraduationMarkID() {
		return graduationMarkID;
	}

	public void setGraduationMarkID(Integer graduationMarkID) {
		this.graduationMarkID = graduationMarkID;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
