package com.erealty.model;

import com.erealty.dbutils.ColumnReflection;

public class Mark {
	public Mark() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Mark(Integer abiturientID, String subj, Integer points) {
		super();
		this.abiturientID = abiturientID;
		this.subj = subj;
		this.points = points;
	}

	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "abiturientID")
	private Integer abiturientID;
	@ColumnReflection(columnName = "subj")
	private String subj;
	// ENUM('ukr', 'math', 'chem', 'phys', 'eng', 'germ', 'span', 'french',
	// 'geogr', 'biol', 'forlit', 'gradcertificate') NOT NULL,
	@ColumnReflection(columnName = "points")
	private Integer points;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getAbiturientID() {
		return abiturientID;
	}

	public void setAbiturientID(Integer abiturientID) {
		this.abiturientID = abiturientID;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Mark [ID=" + ID + ", abiturientID=" + abiturientID + ", subj=" + subj + ", points=" + points + "]";
	}

}
