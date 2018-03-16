package com.ssm.beans;

import java.io.Serializable;

public class BookBean implements Serializable {

	
	private static final long serialVersionUID = -2682305557890221059L;
	private Integer id;
	private String bookName;
	private Integer bookPage;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getBookPage() {
		return bookPage;
	}
	
	public void setBookPage(Integer bookPage) {
		this.bookPage = bookPage;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public BookBean() {
		super();
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", bookName=" + bookName + ", bookPage="
				+ bookPage + ", userId=" + userId + "]";
	}
}

