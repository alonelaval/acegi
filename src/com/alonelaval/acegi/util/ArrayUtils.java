package com.alonelaval.acegi.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alonelaval.acegi.user.User;



/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.util.ArrayUtils.java
 * @createDate 2009-6-10 下午09:11:38	
 */
public class ArrayUtils {
	public  static final  Boolean isEmpty(Object... objects){
		return (objects == null || objects.length == 0);
	}
	
	@SuppressWarnings("unchecked")
	public  static final <T>   T[] toArray(Collection<T> sources,Class cls){
		if(isEmpty(sources))
			return null;
		T [] ts;
		ts =  (T[])Array.newInstance(cls,sources.size());
		ts = sources.toArray(ts);
		return ts;
	} 
	
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("122");
		List<User> list = new ArrayList<User>();
		list.add(user);
		user = new User();
		user.setUsername("hua");
		list.add(user);
		System.out.println(toArray(list,User.class));
		User [] users = toArray(list,User.class);
		for (int i = 0; i < users.length; i++) {
			System.out.println(users[i].getUsername());
		}
	}
}
