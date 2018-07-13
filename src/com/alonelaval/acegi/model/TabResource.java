package com.alonelaval.acegi.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TabResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TabResource implements java.io.Serializable {

	// Fields

	private Long resourceId;
	private String resourceName;
	private String resourceCode;
	private String resourceType;
	private Long resourceParent;
	private Set<TabRoles> tabRoles = new HashSet<TabRoles>(0);

	// Constructors

	/** default constructor */
	public TabResource() {
	}

	/** full constructor */
	public TabResource(String resourceName, String resourceCode,
			String resourceType, Long resourceParent, Set roles) {
		this.resourceName = resourceName;
		this.resourceCode = resourceCode;
		this.resourceType = resourceType;
		this.resourceParent = resourceParent;
		this.tabRoles = roles;
	}

	// Property accessors

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return this.resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceParent() {
		return this.resourceParent;
	}

	public void setResourceParent(Long resourceParent) {
		this.resourceParent = resourceParent;
	}

	public Set<TabRoles> getTabRoles() {
		return tabRoles;
	}

	public void setTabRoles(Set<TabRoles> tabRoles) {
		this.tabRoles = tabRoles;
	}




}