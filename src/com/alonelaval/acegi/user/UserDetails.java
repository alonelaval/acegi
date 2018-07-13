package com.alonelaval.acegi.user;

import java.io.Serializable;

import org.acegisecurity.GrantedAuthority;
import org.omg.CORBA.IRObjectOperations;

import com.alonelaval.acegi.resource.Resource;
import com.alonelaval.acegi.role.Role;
/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.service.UserDetails.java
 * @createDate 2009-6-9 下午09:08:29	
 */
public interface UserDetails extends org.acegisecurity.userdetails.UserDetails {
	public Role [] getRoles();
	public Resource [] getResources();
}
