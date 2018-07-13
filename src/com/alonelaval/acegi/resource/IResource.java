package com.alonelaval.acegi.resource;

import java.io.Serializable;

import com.alonelaval.acegi.role.Role;

/**
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.resource.Resource.java
 * @createDate 2009-6-9 下午09:35:15
 * user resource interfce 
 */
public interface IResource extends  Serializable {
	public Resource [] getResources(Long roleId);
	public Role [] getRoles();
}
 