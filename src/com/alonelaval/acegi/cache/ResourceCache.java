package com.alonelaval.acegi.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataRetrievalFailureException;

import com.alonelaval.acegi.resource.Resource;
import com.alonelaval.acegi.util.Constants;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.cache.ResourceCache.java
 * @createDate 2009-6-13 下午09:32:40	
 */
public class ResourceCache implements InitializingBean{

	private static final Log logger = LogFactory.getLog(ResourceCache.class);

	//~ Instance fields ========================================================

	private Cache cache;
	

	//~ Methods ================================================================

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Cache getCache() {
		return this.cache;
	}

	public Resource getResourceFromCache(String resString) {
		
		Element element = null;

		try {
			element = cache.get(resString);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("Cache failure: " + cacheException.getMessage(), cacheException);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Cache hit: " + (element != null) + "; resString: "
					+ resString);
		}

		if (element == null) {
			return null;
		} else {
			return (Resource) element.getValue();
		}
	}

	public void putResourceInCache(Resource resource) {
		Element element = new Element(resource.getResourceCode(), resource);
		if (logger.isDebugEnabled()) {
			logger.debug("Cache put: " + element.getKey());
		}
		logger.info("Cache put: " + element.getKey());
		this.cache.put(element);
	}

	public void removeResourceFromCache(String resString) {
		if (logger.isDebugEnabled()) {
			logger.debug("Cache remove: " + resString);
		}
		this.cache.remove(resString);
	}

	public List<Resource> getUrlResStrings() {
		return getResourcesByType(Constants.ResourceType.URL);
	}

	public List<Resource> getFunctions() {
		return getResourcesByType(Constants.ResourceType.FUNCTION);
	}

	public List<Resource> getCal() {
		return getResourcesByType(Constants.ResourceType.CAL);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getResourcesByType(String type) {
		List resources;
		List<Resource> resclist = new ArrayList<Resource>();
		try {
			resources = this.cache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			String resString = (String) iter.next();
			Resource resourceDetails = getResourceFromCache(resString);
			if (resourceDetails != null && resourceDetails.getResourceType().equals(type))
				resclist.add(resourceDetails);
		}
		return resclist;
	}

	public void afterPropertiesSet() throws Exception {
		///logger.info("resource initing eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee..............");
	}
}
