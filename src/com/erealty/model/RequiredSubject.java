package com.erealty.model;

import com.erealty.dbutils.ColumnReflection;

public class RequiredSubject {
	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "specialityID")
	private Integer specialityID;
	@ColumnReflection(columnName = "ordinal")	
	private Integer ordinal;
	@ColumnReflection(columnName = "subj")
	private String subj;

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

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	public RequiredSubject() {
		super();
	}

	@Override
	public String toString() {
		return "RequiredSubject [ID=" + ID + ", specialityID=" + specialityID + ", ordinal=" + ordinal + ", subj="
				+ subj + "]";
	}
	
	
}
