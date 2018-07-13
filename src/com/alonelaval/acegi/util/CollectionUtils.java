package com.alonelaval.acegi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.util.ArrayUtils.java
 * @createDate 2009-6-10 下午02:40:53
 */
public class CollectionUtils {
	
	public static final boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	public static final <T> List<T> addAll(T... sources) {
		List<T> list = new ArrayList<T>();
		if (!ArrayUtils.isEmpty(sources))
			for(T t : sources){
				if(t == null)
					continue;
				list.add(t);	
			}
		return list;
	}
	
	public static final <T> List<T> convertToList(Set<T> sources){
		if(!isEmpty(sources)){
			List<T> list = new ArrayList<T>();
			for (Iterator<T> iterator = sources.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				if(t == null)
					continue;
				list.add(t);
			}
			return list;
		}
		return null;
	}
 } 
