/**
 * 
 */
package com.alonelaval.acegi.intercept;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.intercept.method.AbstractMethodDefinitionSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.alonelaval.acegi.resource.Resource;
import com.alonelaval.acegi.role.Role;
import com.alonelaval.acegi.service.ResourceCacheManager;
import com.alonelaval.acegi.util.Constants;

/**
 * @author zhanjia
 * 
 */
public class CacheMethodDefinitionSource extends AbstractMethodDefinitionSource {

	protected static final Log logger = LogFactory
			.getLog(CacheMethodDefinitionSource.class);

	private Boolean protectAllResource;

	private ResourceCacheManager resourceServiceManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.intercept.method.AbstractMethodDefinitionSource#lookupAttributes(java.lang.reflect.Method)
	 */
	@Override
	protected ConfigAttributeDefinition lookupAttributes(Method method) {
		Assert.notNull(method,
				"lookupAttrubutes in the DBMethodDefinitionSource is null");
		String methodString = method.getDeclaringClass().getName() + "."
				+ method.getName();
		logger.info("method name is"+methodString);

		List<Resource> resources = resourceServiceManager
				.getResourcesByType(Constants.ResourceType.FUNCTION);

		if (null == resources) {
			resourceServiceManager.init();
			resources = resourceServiceManager
					.getResourcesByType(Constants.ResourceType.FUNCTION);
		}

		for (Resource resource : resources) {
			if (isMatch(methodString, resource.getResourceCode())
					|| methodString.equals(resource.getResourceCode())) {
				String authoritiesStr = " ";
				ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
				for (Role role : resource.getRoles()) {
					authoritiesStr += role.getRoleCode() + ",";
				}
				authoritiesStr = authoritiesStr.substring(0, authoritiesStr
						.length() - 1);
				logger.info("method is authorities ["+authoritiesStr+"]");
				configAttrEditor.setAsText(authoritiesStr);
				return (ConfigAttributeDefinition) configAttrEditor.getValue();
			}

		}
		return null;
	}

	private boolean isMatch(String methodName, String mappedName) {
		return mappedName.endsWith("*")
				&& methodName.startsWith(mappedName.substring(0, mappedName
						.length() - 1))
				|| mappedName.startsWith("*")
				&& methodName.endsWith(mappedName.substring(1, mappedName
						.length()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
	 */
	public Iterator getConfigAttributeDefinitions() {
		return null;
	}

	public ResourceCacheManager getResourceServiceManager() {
		return resourceServiceManager;
	}

	public void setResourceServiceManager(
			ResourceCacheManager resourceServiceManager) {
		this.resourceServiceManager = resourceServiceManager;
	}

	public Boolean getProtectAllResource() {
		return protectAllResource;
	}

	public void setProtectAllResource(Boolean protectAllResource) {
		this.protectAllResource = protectAllResource;
	}

}
