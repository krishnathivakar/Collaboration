package com.niit.collaboration.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Forum")
public class Forum {

	@Id
	@GeneratedValue
	private int forumId;
	
	private String forumTitle;
	private String forumContent;
	private int userId;
	private String userName;

	@Generated(value = { "" })
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date addDate = new java.sql.Date(new java.util.Date().getTime());

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumContent() {
		return forumContent;
	}

	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	
}
