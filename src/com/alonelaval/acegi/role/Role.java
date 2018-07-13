package com.alonelaval.acegi.role;

import java.io.Serializable;

import com.alonelaval.acegi.resource.Resource;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.role.Role.java
 * @createDate 2009-6-9 下午10:05:06
 */
public class Role implements Serializable {
	
	
	private static final long serialVersionUID = -6423086556549807726L;
	
	
	private Long roleId;
	private String roleName;
	private String roleCode;
	private String status;
	
	private Resource [] resources;
	
	public Resource[] getResources() {
		return resources;
	}

	public void setResources(Resource[] resources) {
		this.resources = resources;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public boolean equals(Object rhs) {
		if (!(rhs instanceof Role))
			return false;
		Role resauth = (Role) rhs;
		if (!getRoleCode().equals(resauth.getRoleCode()))
			return false;
		if (!getStatus().equals(resauth.getStatus()))
			return false;
		if (resauth.getResources().length != getResources().length)
			return false;
		for (int i = 0; i < getResources().length; i++) {
			if (!getResources()[i].equals(resauth.getResources()[i]))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 168;
		if (getResources() != null) {
			for (int i = 0; i < getResources().length; i++)
				code *= getResources()[i].hashCode() % 7;
		}
		if (getRoleCode() != null)
			code *= getRoleCode().hashCode() % 7;
		return code;
	}
}
