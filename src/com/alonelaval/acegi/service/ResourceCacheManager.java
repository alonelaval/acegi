package com.alonelaval.acegi.service;

import java.util.List;

import com.alonelaval.acegi.resource.Resource;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.service.ResourceCacheManager.java
 * @createDate 2009-6-13 下午11:15:50	
 */
public interface ResourceCacheManager {
	
	void init();
	Resource getResourceFromCache(String resourceCode);
	void putResourceInCache(Resource resource);
	void removeResourceFromCache(String resString);
	List<Resource> getResourcesByType(String type);
 	
}
