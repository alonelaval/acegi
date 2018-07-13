package com.alonelaval.acegi.dao;

import java.io.Serializable;
import java.util.List;

import com.alonelaval.acegi.resource.Resource;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.dao.Resource.java
 * @createDate 2009-6-13 下午10:37:12	
 */
public interface ResourceServce extends Serializable {
	List<Resource> getAllResource();
}
