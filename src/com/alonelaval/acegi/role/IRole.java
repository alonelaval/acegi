package com.alonelaval.acegi.role;

import java.io.Serializable;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.role.IRole.java
 * @createDate 2009-6-9 下午10:04:59	
 */
public interface IRole extends Serializable {
	
	public Role [] getRoles(Long userId);
	
}
