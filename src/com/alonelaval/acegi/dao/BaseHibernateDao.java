package com.alonelaval.acegi.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.alonelaval.acegi.web.PageSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.dao.BaseHibernateDao.java
 * @createDate 2009-6-10 上午10:00:59
 */
public abstract class BaseHibernateDao extends HibernateDaoSupport {

	protected static Logger logger = null;

	public BaseHibernateDao() {
		logger = LoggerFactory.getLogger(getClass());
	}

	/**
	 * 通过实例查找
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(T entity) {
		Assert.notNull(entity);
		return (List<T>) getHibernateTemplate().findByExample(entity);
	}

	/**
	 * 通过主键查找
	 * 
	 * @param entity
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T, PK extends Serializable> T get(T entity, PK id) {
		Assert.notNull(entity);
		Assert.notNull(id);
		return (T) getHibernateTemplate().get(entity.getClass(), id);
	}

	/**
	 * 通过主键查找
	 * 
	 * @param t
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T, PK extends Serializable> T load(T entity, PK id) {
		Assert.notNull(entity);
		Assert.notNull(id);
		return (T) getHibernateTemplate().load(entity.getClass(), id);
	}

	/**
	 * 通过LOAD得到实例集合
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(T entity) {
		Assert.notNull(entity);
		return (List<T>) getHibernateTemplate().loadAll(entity.getClass());
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public <T> void save(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().save(entity);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public <T> void update(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().update(entity);
	}

	/**
	 * merge
	 * 
	 * @param t
	 * @deprecated
	 */
	public <T> void modify(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().merge(entity);
	}

	/**
	 * 保存或修改
	 * 
	 * @param t
	 */
	public <T> void saveOrUpdate(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 批量跟新或修改
	 * 
	 * @param entitys
	 */
	public <T> void saveOrUpdateAll(Collection<T> entitys) {
		Assert.notEmpty(entitys);
		getHibernateTemplate().saveOrUpdateAll(entitys);

	}

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public <T> void delete(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 批量删除
	 * 
	 * @param entitys
	 */
	public <T> void deleteAll(Collection<T> entitys) {
		Assert.notEmpty(entitys);
		getHibernateTemplate().deleteAll(entitys);
	}

	/**
	 * 查找部分的记录
	 * 
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getPartlist(int firstResult, int maxResults, T entity) {
		Assert.notNull(entity);
		Criteria criteria = getSession().createCriteria(entity.getClass());
		if (firstResult > 0) {
			criteria.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			criteria.setMaxResults(maxResults);
		}

		return (List<T>) criteria.list();
	}

	/**
	 * 根据HQL查
	 * 
	 * @param <T>
	 * @param hql
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql) {
		return (List<T>) getHibernateTemplate().find(hql);
	}

	/**
	 * 根据detachedCriteria查
	 * 
	 * @param <T>
	 * @param detachedCriteria
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) getHibernateTemplate()
				.findByCriteria(detachedCriteria);
	}

	/**
	 * 根据条件Key的名称查询
	 * 
	 * @param <T>
	 * @return
	 * @see from School school where school.ZoneId = :zoneId Map map = new
	 *      HashMap(); map.put("zoneId",111); key is zoneId value is map of key
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByConditionKey(final String hql,
			final Map<String, Object> condition) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (Map.Entry<String, Object> entry : condition
								.entrySet()) {
							query
									.setParameter(entry.getKey(), entry
											.getValue());
						}
						return query.list();
					}
				});
	}

	/**
	 * 根据条件Key的position位置查询
	 * 
	 * @param <T>
	 * @param hql
	 * @param condition
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByConditionPosition(final String hql,
			final Map<Integer, Object> condition) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						@SuppressWarnings("unused")
						Query query = session.createQuery(hql);

						for (@SuppressWarnings("unused")
						Map.Entry<Integer, Object> entry : condition.entrySet()) {
							query.setParameter(entry.getKey().intValue(), entry
									.getValue());
						}
						return query.list();
					}
				});
	}

	/**
	 * 使用?占位符
	 * 
	 * @param <T>
	 * @param hql
	 * @param objects
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByObjects(String hql, Object... objects) {
		return (List<T>) getHibernateTemplate().find(hql, objects);
	}

	/**
	 * 使用?占位符
	 * 
	 * @param <T>
	 * @param hql
	 * @param object
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByObject(String hql, Object object) {
		return (List<T>) getHibernateTemplate().find(hql, object);
	}

	/**
	 * 得到中的行数
	 * @param detachedCriteria
	 * @return
	 * @see
	 */
	public int getTotalCount(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		int totalCount = ((Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		return totalCount;
	}
	/**
	 * 得到总行数
	 * @param hql
	 * @param condition
	 * @return
	 * @see
	 */
	public int getTotalCount(String hql,Map<Integer,Object> condition){
		Query query = getSession().createQuery(hql);
		for (Map.Entry<Integer, Object> entry : condition.entrySet()) {
			query.setParameter(entry.getKey().intValue(),entry.getValue());
		}
		query.setProperties(Projections.rowCount());
		return Integer.parseInt(query.uniqueResult().toString());	
	}

	/**
	 * detachedCriteria分页
	 * 
	 * @param detachedCriteria
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public <T> PageSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex,
			final int pageSize) {
		Assert.notNull(detachedCriteria);
		return (PageSupport) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(final Session session)
							throws HibernateException, SQLException {
						final Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);

						int totalCount = ((Integer) criteria.setProjection(
								Projections.rowCount()).uniqueResult())
								.intValue();
						criteria.setProjection(null);
						List<T> items = criteria.setFirstResult(
								startIndex >= 1 ? startIndex : 0)
								.setMaxResults(pageSize >= 1 ? pageSize : 20)
								.list();
						PageSupport pageSupport = new PageSupport();
						pageSupport.setTotalCount(totalCount);
						pageSupport.setItems(items);
						pageSupport.setStartIndex(startIndex);
						pageSupport.setPageSize(pageSize);

						return pageSupport;
					}
				}, true);
	}

}
