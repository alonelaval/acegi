package com.alonelaval.acegi.user;


import org.acegisecurity.GrantedAuthority;

import com.alonelaval.acegi.resource.Resource;
import com.alonelaval.acegi.role.Role;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.user.User.java
 * @createDate 2009-6-9 下午09:14:19	
 */
public class User implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5847627906101402090L;
	
	
	private String password;
    private String username;
    private GrantedAuthority[] authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Resource [] resources;
    private Role [] roles;
    
    
    public User(){}
    public User(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities)
            throws IllegalArgumentException {
            if (((username == null) || "".equals(username)) || (password == null)) {
                throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
            }
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            setAuthorities(authorities);
            
        }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Resource[] getResources() {
		return resources;
	}
	public void setResources(Resource[] resources) {
		this.resources = resources;
	}
	public Role[] getRoles() {
		return roles;
	}
	public void setRoles(Role[] roles) {
		this.roles = roles;
	}
	
	@Override
	public boolean equals(Object rhs) {
		if (!(rhs instanceof User))
			return false;
		User resauth = (User) rhs;
		if (!getUsername().equals(resauth.getUsername()))
			return false;
		if (isEnabled() != resauth.isEnabled())
			return false;
		if (resauth.getRoles().length != getRoles().length)
			return false;
		for (int i = 0; i < getRoles().length; i++) {
			if (!getRoles()[i].equals(resauth.getRoles()[i]))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 168;
		if (getRoles() != null) {
			for (int i = 0; i < getRoles().length; i++)
				code *= getRoles()[i].hashCode() % 7;
		}
		if (getUsername() != null)
			code *= getUsername().hashCode() % 7;
		return code;
	}
}
