package com.alonelaval.acegi.util;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


/***
 * 
 * @author huawei
 *
 */
public class HibernateHelper {
	/***************************************************************************
	 * 比较字符串
	 * 
	 * @param d
	 * @param cond
	 * @return
	 */
	public static DetachedCriteria compareByEqString(DetachedCriteria d,
			Map<String, String> cond) {
		if (cond.isEmpty())
			return d;
		Iterator it = cond.keySet().iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			if (null == cond.get(str) || "".equals(cond.get(str)))
				continue;
			d.add(Restrictions.eq(str, cond.get(str)));
		}
		return d;
	}
	/**
	 * 只要是NUNBER类型 就调用此方法
	 * @param d
	 * @param cond
	 * @return
	 */
	public  static  DetachedCriteria compareByEqNumber(DetachedCriteria d,Map<String, ? extends Number> cond){
		if (cond.isEmpty())
			return d;
		Iterator it = cond.keySet().iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			if (null == cond.get(str))
				continue;
			
			
			d.add(Restrictions.eq(str, cond.get(str)));
		}
		return d;
	}
	/**
	 * 比较时间
	 * 
	 * @param d
	 * @param cond
	 * @param isStart
	 * @return
	 */
	public static DetachedCriteria compareByDate(DetachedCriteria d,
			Map<String, Date[]> cond, Boolean[] isStart) {
		if (null == cond || cond.isEmpty())
			return d;
		if (null == isStart || isStart.length == 0) {
			isStart = new Boolean[] { Boolean.TRUE, Boolean.FALSE };
		}
		Iterator it = cond.keySet().iterator();
		
		while (it.hasNext()) {
			String o = (String) it.next();
		
			
			
			Date [] dates=cond.get(o);
			
			
			int i = 0;
			for(Date date :dates)
			{
				
					
				if (null == date || "".equals(date))
						continue;
					
				if (isStart[i]){
					d.add(Restrictions.ge(o, date));
					
				}
				else {
					
					d.add(Restrictions.le(o, date));
				}
				i++;
			}
		}
		
		return d;
	}

	/***************************************************************************
	 * 排序
	 * 
	 * @param <T>
	 * @param pageRequest
	 * @param detachedCriteria
	 * @return
	 */
//	public static <T extends PageRequest> DetachedCriteria orderByDetachedCriteria(
//			T pageRequest, DetachedCriteria detachedCriteria) {
//		if (pageRequest.getOrderBy() != null
//				&& pageRequest.getOrderBy().length() > 0) {
//			if ("ASC".equals(pageRequest.getOrderDir()))
//				detachedCriteria.addOrder(Order.asc(pageRequest.getOrderBy()));
//			else if ("DESC".equals(pageRequest.getOrderDir()))
//				detachedCriteria.addOrder(Order.desc(pageRequest.getOrderBy()));
//		}
//		return detachedCriteria;
//	}

	/**
	 * 在某某里面
	 * 
	 * @param d
	 * @param strName
	 * @param data
	 * @return
	 */
	public static DetachedCriteria compareByIn(DetachedCriteria d, String strName,
			String[] data) {
		if (null != strName && null != data && data.length > 0) {
			d.add(Restrictions.in(strName, data));
		}
		return d;
	}

}
