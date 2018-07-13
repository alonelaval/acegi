package com.alonelaval.acegi.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TabUsers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TabUsers implements java.io.Serializable {

	// Fields

	private Long userId;
	private String loginName;
	private String userName;
	private String userPwd;
	private String userStatus;
	private String userDesc;
	private Set<TabRoles> roles = new HashSet<TabRoles>(0);

	// Constructors

	/** default constructor */
	public TabUsers() {
	}

	/** minimal constructor */
	public TabUsers(String loginName, String userName, String userPwd,
			String userStatus) {
		this.loginName = loginName;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userStatus = userStatus;
	}

	/** full constructor */
	public TabUsers(String loginName, String userName, String userPwd,
			String userStatus, String userDesc, Set roles) {
		this.loginName = loginName;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userStatus = userStatus;
		this.userDesc = userDesc;
		this.roles = roles;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}


}