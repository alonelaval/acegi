package com.alonelaval.acegi.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.cache.EhCacheBasedUserCache.java
 * @createDate 2009-6-13 下午04:47:02	
 */
public class EhCacheBasedUserCache implements UserCache,InitializingBean {
	//~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(EhCacheBasedUserCache.class);

    //~ Instance fields ================================================================================================

    private Ehcache cache;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache, "cache mandatory");
    }

    public Ehcache getCache() {
        return cache;
    }

    public UserDetails getUserFromCache(String username) {
        Element element = null;

        try {
            element = cache.get(username);
        } catch (CacheException cacheException) {
            throw new DataRetrievalFailureException("Cache failure: " + cacheException.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Cache hit: " + (element != null) + "; username: " + username);
        }

        if (element == null) {
            return null;
        } else {
            return (UserDetails) element.getValue();
        }
    }
    public String getUserNameFromCache(String str){
        Element element = null;

        try {
            element = cache.get(str);
        } catch (CacheException cacheException) {
            throw new DataRetrievalFailureException("Cache failure: " + cacheException.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Cache hit: " + (element != null) + "; username: " + str);
        }
     //System.out.println("拿取...。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        if (element == null) {
            return null;
        } else {
            return (String) element.getValue();
        }
    }
    public void putUserInCache(UserDetails user) {
        Element element = new Element(user.getUsername(), user);
        Element element2 = new Element("username",user.getUsername());
        if (logger.isDebugEnabled()) {
            logger.debug("Cache put: " + element.getKey());
            logger.debug("Cache put: "+element2.getKey());
        }
      // System.out.println("放入缓存。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        cache.put(element2);
        cache.put(element);
    }

    public void removeUserFromCache(UserDetails user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Cache remove: " + user.getUsername());
        }

        this.removeUserFromCache(user.getUsername());
        this.removeUserFromCache("username");
    }

    public void removeUserFromCache(String username) {
        cache.remove(username);
    }

    public void setCache(Ehcache cache) {
        this.cache = cache;
    }

}
