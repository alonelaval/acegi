package com.alonelaval.acegi.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TabRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TabRoles implements java.io.Serializable {

	// Fields

	private Long roleId;
	private String roleName;
	private String roleCode;
	private String status;
	private Set<TabUsers> users = new HashSet<TabUsers>(0);
	private Set<TabResource> resources = new HashSet<TabResource>(0);

	// Constructors

	/** default constructor */
	public TabRoles() {
	}

	/** minimal constructor */
	public TabRoles(String roleCode) {
		this.roleCode = roleCode;
	}

	/** full constructor */
	public TabRoles(String roleName, String roleCode, String status,
			Set resources, Set users) {
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.status = status;
		this.users = users;
		this.resources = resources;
	}

	// Property accessors

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Set getResources() {
		return resources;
	}

	public void setResources(Set resources) {
		this.resources = resources;
	}

	

}