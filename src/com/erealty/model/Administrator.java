package com.erealty.model;

import com.erealty.dbutils.ColumnReflection;

public class Administrator {
	@ColumnReflection(columnName = "ID")
	private Integer ID;
	@ColumnReflection(columnName = "login")
	private String login;
	@ColumnReflection(columnName = "password")
	private String password;
	@ColumnReflection(columnName = "photoPath")
	private String photoPath;

	public Administrator() {
		super();
	}

	public Administrator(String login, String password, String photoPath) {
		super();
		this.login = login;
		this.password = password;
		this.photoPath = photoPath;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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

	@Override
	public String toString() {
		return "User [id=" + ID + ", login=" + login + ", password=" + password;
	}

}
