package com.alonelaval.acegi.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alonelaval.acegi.cache.ResourceCache;
import com.alonelaval.acegi.dao.ResourceServce;
import com.alonelaval.acegi.resource.Resource;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.service.AcegiResourceService.java
 * @createDate 2009-6-13 下午10:34:19	
 */
public class AcegiResourceService implements ResourceCacheManager,InitializingBean{
	private static final Log logger = LogFactory.getLog(AcegiResourceService.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3899366518635200973L;
	private ResourceCache resourceCache;
	private ResourceServce resourceSerivce;
	
	public ResourceServce getResourceSerivce() {
		return resourceSerivce;
	}

	public void setResourceSerivce(ResourceServce resourceSerivce) {
		this.resourceSerivce = resourceSerivce;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public ResourceCache getResourceCache() {
		return resourceCache;
	}

	public void setResourceCache(ResourceCache resourceCache) {
		this.resourceCache = resourceCache;
	}


	/**
	 * 初始化
	 */
	public void init() {
		List<Resource> resources = resourceSerivce.getAllResource();
		for (Resource resource : resources) {
			resourceCache.putResourceInCache(resource);
		}
		logger.info("resourcesManager  initing...  resourceSerivce");
	}

	public Resource getResourceFromCache(String resourceCode) {
	
		return resourceCache.getResourceFromCache(resourceCode);
	}

	public List<Resource> getResourcesByType(String type) {
		return resourceCache.getResourcesByType(type);
	}

	public void putResourceInCache(Resource resource) {
		resourceCache.putResourceInCache(resource);
	}

	public void removeResourceFromCache(String resString) {
		resourceCache.removeResourceFromCache(resString);
	}

	public void afterPropertiesSet() throws Exception {
		logger.info("AcegiResourceService init ............................................");
		init();
	}
	
	
}
