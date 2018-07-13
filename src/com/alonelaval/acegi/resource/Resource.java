package com.alonelaval.acegi.resource;

import java.io.Serializable;

import com.alonelaval.acegi.role.Role;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.resource.Resource.java
 * @createDate 2009-6-9 下午10:02:05	
 */
public class Resource implements IResource,Serializable {
	
	private static final long serialVersionUID = 296452743447861219L;
	private Role [] roles;
	private Long resourceId;
	private String resourceName;
	private String resourceCode;
	private String resourceType;
	private Long resourceParent;
	
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public Long getResourceParent() {
		return resourceParent;
	}
	public void setResourceParent(Long resourceParent) {
		this.resourceParent = resourceParent;
	}
	public Resource[] getResources(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Role[] getRoles() {
		// TODO Auto-generated method stub
		return roles;
	}
	public void setRoles(Role[] roles) {
		this.roles = roles;
	}
	@Override
	public boolean equals(Object rhs) {
		if (!(rhs instanceof Resource))
			return false;
		Resource resauth = (Resource) rhs;
		if (!getResourceCode().equals(resauth.getResourceCode()))
			return false;
		if (!getResourceType().equals(resauth.getResourceType()))
			return false;
		if (resauth.getRoles().length != getRoles().length)
			return false;
		for (int i = 0; i < getRoles().length; i++) {
			if (!getRoles()[i].equals(resauth.getRoles()[i]))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 168;
		if (getRoles() != null) {
			for (int i = 0; i < getRoles().length; i++)
				code *= getRoles()[i].hashCode() % 7;
		}
		if (getResourceCode() != null)
			code *= getResourceCode().hashCode() % 7;
		return code;
	}
}
