package com.alonelaval.acegi.cache;


/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.cache.UserCache.java
 * @createDate 2009-6-13 下午04:57:15	
 */
public interface UserCache extends org.acegisecurity.providers.dao.UserCache{

	String getUserNameFromCache(String str);
}
