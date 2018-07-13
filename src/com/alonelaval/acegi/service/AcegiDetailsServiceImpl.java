package com.alonelaval.acegi.service;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.alonelaval.acegi.cache.ResourceCache;
import com.alonelaval.acegi.dao.IUserSerivce;
import com.alonelaval.acegi.role.Role;
import com.alonelaval.acegi.user.User;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.service.AcegiDetailsService.java
 * @createDate 2009-6-10 下午10:11:27	
 */
public class AcegiDetailsServiceImpl implements UserDetailsService {
	
	private static final Log logger = LogFactory.getLog(AcegiDetailsServiceImpl.class);
	private IUserSerivce userSerivce;
//	private static final String rolePrefix="ROLE_";
	
	public IUserSerivce getUserSerivce() {
		return userSerivce;
	}

	public void setUserSerivce(IUserSerivce userSerivce) {
		this.userSerivce = userSerivce;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
			User user = userSerivce.getUser(username, "");
			if(null == user){
				throw new UsernameNotFoundException("User not found");
			}
			if(user.getRoles() == null )
			{
				throw new UsernameNotFoundException("User not found");
			}
			Role [] roles = user.getRoles();
			GrantedAuthority [] authorities = new GrantedAuthority[user.getRoles().length];
			for (int i = 0; i < roles.length; i++) {
				authorities[i] = new GrantedAuthorityImpl(roles[i].getRoleCode());
				if(logger.isDebugEnabled()){
					logger.debug("user authorities is ["+roles[i].getRoleCode()+"]!");
				}
			}
			user.setAuthorities(authorities);
		return user;
	}
}
