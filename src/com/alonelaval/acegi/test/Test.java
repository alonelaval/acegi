package com.alonelaval.acegi.test;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.alonelaval.acegi.common.HibernateSessionFactory;
import com.alonelaval.acegi.dao.IUserSerivce;
import com.alonelaval.acegi.dao.impl.UserSerivceImpl;
import com.alonelaval.acegi.model.TabRoles;
import com.alonelaval.acegi.model.TabUsers;




/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.service.Test.java
 * @createDate 2009-6-9 下午08:00:30	
 */
public class Test {
	
	public static void main(String[] args) {
		Session s=HibernateSessionFactory.getSession();
	   Transaction tx = s.beginTransaction();
		tx.begin();
	  /* TabUsers users = new TabUsers();
		users.setLoginName("123");
		users.setUserName("huawei");
		users.setUserPwd("pwd");
		users.setUserStatus("001");
		s.save(users);*/
		
		//String hql = "From TabUsers as a left join fetch a.tabUserRoles as b left join fetch b.tabRoles as c left join fetch c.tabRoleResources as d left join fetch d.tabResource as e where a.loginName = 'huawei' ";
		//String hql = "From TabUsers as a left outer join fetch a.tabUserRoles as b left join fetch b.tabRoleResources where a.loginName='huawei'";
//		String hql ="From TabUsers as a  where a.loginName='huawei'";
//		Query query = s.createQuery(hql);
//		List<TabUsers> users= query.list() ;
		
		tx.commit();
		
		
		IUserSerivce user = new UserSerivceImpl();
		TabUsers uuu= null;
		
		
		
		//System.out.println(uuu);
		//uuu = user.getUser(7L);//user.load(7L);
		System.out.println(uuu);
//		DetachedCriteria criteria = DetachedCriteria.forClass(TabRoles.class);
//		criteria.createAlias("users", "users").add(Restrictions.eq("users.id",7L));
//		Criteria criteria2 = criteria.getExecutableCriteria(s);
//		List<TabRoles> list = criteria2.list();
//		
//		System.out.println(list.size());
//		for (TabUsers tabUsers : users) {
//			System.out.println("loginName:==="+tabUsers.getLoginName());
//			for ( Iterator<TabRoles> iterator = tabUsers.getRoles().iterator();iterator.hasNext();) {
//				TabRoles role = iterator.next();
//				System.out.println("roleCode:==="+role.getRoleCode());
//				System.out.println("roleCode:==="+role.getRoleId());
//				for (Iterator<TabResource> iterator2 = role.getResources().iterator(); iterator2.hasNext();) {
//					TabResource resource= iterator2.next();
//					System.out.println("resourceCode:==="+resource.getResourceCode());
//					System.out.println("resource.get(0).getTabRoles();"+resource.getTabRoles().size());
//					
//				}
//			}
//		}
		s.close();
		//test1(list);
//		List<Integer> list = new  ArrayList<Integer>();
//		list.add(new Integer(11));
		//test1(list);
		
		//test2(list);
		//System.out.println(users.getUserId());
		
	}
	public static void test1(List<? extends TabRoles> list){
	//	System.out.println(((Class<?>)((ParameterizedType)list.getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
		//System.out.println((Class<?>) ((ParameterizedType) list.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		//sun.reflect.generics.reflectiveObjects.TypeVariableImpl
	}
	
	public  static <T extends Number>  void test2(List<T> list ){
		System.out.println(((Class<T>)((ParameterizedType)list.getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
	}
}
