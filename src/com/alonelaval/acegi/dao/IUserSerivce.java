package com.alonelaval.acegi.dao;

import com.alonelaval.acegi.model.TabUsers;
import com.alonelaval.acegi.user.User;



/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.dao.IUserSerivce.java
 * @createDate 2009-6-10 下午12:14:08	
 */
public interface IUserSerivce{
	public User getUser(String name, String pwd);
	public void addUser(User user);
	public void romoveUser(User user);
	public User getUser(Long uid);
	public void updateUser(User user);
}
