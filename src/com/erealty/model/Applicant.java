package com.erealty.model;

public class Applicant implements Comparable<Applicant> {
	private String abiturFullName;
	private String mark1Subj;
	private Integer mark1Points;
	private String mark2Subj;
	private Integer mark2Points;
	private String mark3Subj;
	private Integer mark3Points;
	private String mark4Subj;
	private Integer mark4Points;

	public Applicant(String abiturFullName, String mark1Subj, Integer mark1Points, String mark2Subj,
			Integer mark2Points, String mark3Subj, Integer mark3Points, String mark4Subj, Integer mark4Points) {
		super();
		this.abiturFullName = abiturFullName;
		this.mark1Subj = mark1Subj;
		this.mark1Points = mark1Points;
		this.mark2Subj = mark2Subj;
		this.mark2Points = mark2Points;
		this.mark3Subj = mark3Subj;
		this.mark3Points = mark3Points;
		this.mark4Subj = mark4Subj;
		this.mark4Points = mark4Points;
	}

	public String getAbiturFullName() {
		return abiturFullName;
	}

	public void setAbiturFullName(String abiturFullName) {
		this.abiturFullName = abiturFullName;
	}

	public String getMark1Subj() {
		return mark1Subj;
	}

	public void setMark1Subj(String mark1Subj) {
		this.mark1Subj = mark1Subj;
	}

	public Integer getMark1Points() {
		return mark1Points;
	}

	public void setMark1Points(Integer mark1Points) {
		this.mark1Points = mark1Points;
	}

	public String getMark2Subj() {
		return mark2Subj;
	}

	public void setMark2Subj(String mark2Subj) {
		this.mark2Subj = mark2Subj;
	}

	public Integer getMark2Points() {
		return mark2Points;
	}

	public void setMark2Points(Integer mark2Points) {
		this.mark2Points = mark2Points;
	}

	public String getMark3Subj() {
		return mark3Subj;
	}

	public void setMark3Subj(String mark3Subj) {
		this.mark3Subj = mark3Subj;
	}

	public Integer getMark3Points() {
		return mark3Points;
	}

	public void setMark3Points(Integer mark3Points) {
		this.mark3Points = mark3Points;
	}

	public String getMark4Subj() {
		return mark4Subj;
	}

	public void setMark4Subj(String mark4Subj) {
		this.mark4Subj = mark4Subj;
	}

	public Integer getMark4Points() {
		return mark4Points;
	}

	public void setMark4Points(Integer mark4Points) {
		this.mark4Points = mark4Points;
	}

	@Override
	public int compareTo(Applicant o) {
		return -(mark1Points - o.getMark1Points() + mark2Points - o.getMark2Points() + mark3Points - o.getMark3Points()
				+ mark4Points - o.getMark4Points());
	}
}
