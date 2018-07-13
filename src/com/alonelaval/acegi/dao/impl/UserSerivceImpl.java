package com.alonelaval.acegi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alonelaval.acegi.dao.GenericHibernateDao;
import com.alonelaval.acegi.dao.IUserSerivce;
import com.alonelaval.acegi.model.TabRoles;
import com.alonelaval.acegi.model.TabUsers;
import com.alonelaval.acegi.role.Role;
import com.alonelaval.acegi.user.User;
import com.alonelaval.acegi.util.ArrayUtils;
import com.alonelaval.acegi.util.CollectionUtils;
import com.alonelaval.acegi.util.Constants;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.dao.impl.UserSerivceImpl.java
 * @createDate 2009-6-10 下午12:19:01
 */
public class UserSerivceImpl extends GenericHibernateDao<TabUsers,Long> implements IUserSerivce {

	public void addUser(User user) {
		// save(user);
	}

	@SuppressWarnings("unchecked")
	public User getUser(String name, String pwd) {
		String hql = "From TabUsers as t where t.loginName = ? ";
		List<TabUsers> list = super.findByObjects(hql, name);
		
		User user = null;
		if (!CollectionUtils.isEmpty(list)) {
			TabUsers users = list.get(0);
			Set<TabRoles> tab = users.getRoles();
			user = new User();
			user.setPassword(users.getUserPwd());
			user.setUsername(users.getLoginName());
			user.setEnabled((users.getUserStatus().equals(Constants.UserStatus.ENABLE)));
			user.setCredentialsNonExpired(true);
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setUsername(user.getUsername());
			List<Role> roles = new ArrayList<Role>();
			for (TabRoles tabRole : tab) {
				Role role = new Role();
				role.setRoleCode(tabRole.getRoleCode());
				role.setRoleId(tabRole.getRoleId());
				role.setRoleName(tabRole.getRoleName());
				role.setStatus(tabRole.getStatus());
				roles.add(role);
			}
			user.setRoles(ArrayUtils.toArray(roles, Role.class));
		}
		
		TabUsers sUser  =this.get(7L);
		System.out.println(sUser);
		TabUsers ssUser = this.load(7l);
		System.out.println(ssUser);
		
		if(user == null)
			return null;
		return user;
	}

	public User getUser(Long uid) {
		//return super.get(uid);
		return null;
	}

	public void romoveUser(User user) {

	}

	public void updateUser(User user) {

	}

	public void testaa() {
		logger.info("test");
	}

	public static void main(String[] args) {
		UserSerivceImpl userSerivceImpl = new UserSerivceImpl();
		userSerivceImpl.testaa();
		//logger.info("aaaaaaaaaaaaaaaaa");
		//logger.info("bbbbbbbbbbbbbbbbbbbbb");
	}
}
