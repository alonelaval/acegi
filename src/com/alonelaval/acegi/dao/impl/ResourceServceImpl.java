package com.alonelaval.acegi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.alonelaval.acegi.dao.BaseHibernateDao;
import com.alonelaval.acegi.dao.ResourceServce;
import com.alonelaval.acegi.model.TabResource;
import com.alonelaval.acegi.model.TabRoles;
import com.alonelaval.acegi.resource.Resource;
import com.alonelaval.acegi.role.Role;
import com.alonelaval.acegi.util.ArrayUtils;
import com.alonelaval.acegi.util.BeanUtil;
import com.alonelaval.acegi.util.CollectionUtils;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.dao.impl.ResourceServceImpl.java
 * @createDate 2009-6-13 下午10:39:03
 */
public class ResourceServceImpl extends BaseHibernateDao implements
		ResourceServce {

	private static final long serialVersionUID = -5560411131223132508L;

	public List<Resource> getAllResource() {
		String hql = "From TabResource t ";
		List<TabResource> tabResourcesList = find(hql);
		List<Resource> resources = new ArrayList<Resource>();
		for (TabResource tabResource : tabResourcesList) {
			Resource resource = BeanUtil.copyProPerties(new Resource(),
					tabResource);
			List<TabRoles> tabRoles = CollectionUtils.convertToList(tabResource.getTabRoles());
			List<Role> rolesList = new ArrayList<Role>();
			for (TabRoles tabRoles2 : tabRoles) {
				Role role = new Role();
				role.setRoleId(tabRoles2.getRoleId());
				role.setRoleCode(tabRoles2.getRoleCode());
				role.setStatus(tabRoles2.getStatus());
				role.setRoleName(tabRoles2.getRoleName());
				rolesList.add(role);
			}
			resource.setRoles(ArrayUtils.toArray(rolesList, Role.class));
			resources.add(resource);
		}
		return resources;
	}

}
