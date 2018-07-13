package com.alonelaval.acegi.intercept;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.intercept.web.AbstractFilterInvocationDefinitionSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.alonelaval.acegi.cache.UserCache;
import com.alonelaval.acegi.resource.Resource;
import com.alonelaval.acegi.role.Role;
import com.alonelaval.acegi.service.ResourceCacheManager;
import com.alonelaval.acegi.user.UserDetails;
import com.alonelaval.acegi.util.CollectionUtils;
import com.alonelaval.acegi.util.Constants;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.intercept.CacheObjectDefinitionSource.java
 * @createDate 2009-6-13 下午12:42:05
 */
public class CacheObjectDefinitionSource extends
		AbstractFilterInvocationDefinitionSource {
	private static final Log logger = LogFactory
			.getLog(CacheObjectDefinitionSource.class);
	// 缓存管理器
	//private UserCache userCache;
	//private UserDetails userDetails;
	private ResourceCacheManager resourceServiceManager;
	// private EhCacheManagerFactoryBean cacheManager;
	private boolean protectAllResource = false;
	private PathMatcher pathMatcher = new AntPathMatcher();

	private final PatternMatcher matcher = new Perl5Matcher();

	private boolean convertUrlToLowercaseBeforeComparison = false;

	private boolean useAntPath = true;

	public ConfigAttributeDefinition getAttributes(Object object) {
		try {
			return super.getAttributes(object);
		} catch (IllegalArgumentException e) {
			return this.lookupAttributes((String) object);
		}
	}

	@Override
	public ConfigAttributeDefinition lookupAttributes(String url) {

		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}

		// resourceServiceManager.init();

		List<Resource> resources = resourceServiceManager
				.getResourcesByType(Constants.ResourceType.URL);

		if (CollectionUtils.isEmpty(resources)) {
			resourceServiceManager.init();
			resources = resourceServiceManager
					.getResourcesByType(Constants.ResourceType.URL);
		}

		for (Resource resource : resources) {
			String srcString = url.toLowerCase();
			String tagString = resource.getResourceCode();
			if (isConvertUrlToLowercaseBeforeComparison()) {
				url = url.toLowerCase();
				tagString = tagString.toLowerCase();
			}
			if (isResourceMatch(isUseAntPath(), srcString, tagString)) {
				logger.info("url===============" + srcString + "  tagUrl"
						+ tagString + "        resource.length"
						+ resource.getRoles().length);
				return getCadByAuthorities(resource.getRoles(),
						isProtectAllResource());
			}

		}
		logger.info("return null value");
		return null;
	}

	public Iterator getConfigAttributeDefinitions() {
		return null;
	}

	/**
	 * 把权限组转为 ConfigAttributeDefinition
	 * 
	 * @param authorities
	 *            权限
	 * @param isProtectAllResource
	 *            是否保护所有资源，true，则所有资源默认为受保护， false则只有声明了并且与权限挂钩了的资源才会受保护
	 * @return
	 */
	public static ConfigAttributeDefinition getCadByAuthorities(Role[] roles,
			boolean isProtectAllResource) {

		if (roles == null || roles.length == 0) {
			if (isProtectAllResource) {
				return new ConfigAttributeDefinition();
			} else {
				return null;
			}
		}
		logger.info("authStr:.............................roles.length"
				+ roles.length);
		ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
		String authoritiesStr = " ";

		for (Role role : roles) {
			authoritiesStr += role.getRoleCode() + ",";
		}

		String authStr = authoritiesStr.substring(0,
				authoritiesStr.length() - 1);

		configAttrEditor.setAsText(authStr);
		logger.info("authStr:............................." + authStr);
		return (ConfigAttributeDefinition) configAttrEditor.getValue();
	}

	/**
	 * 根据是否使用UseAntPath和是否字符小写化来预先格式化url
	 * 
	 * @param url
	 * @param isUseAntPath
	 * @param isToLowercase
	 * @return
	 */
	private String preDealUrl(String url, boolean isUseAntPath,
			boolean isToLowercase) {
		if (isUseAntPath) {
			// Strip anything after a question mark symbol, as per SEC-161.
			int firstQuestionMarkIndex = url.lastIndexOf("?");

			if (firstQuestionMarkIndex != -1) {
				url = url.substring(0, firstQuestionMarkIndex);
			}
		}
		if (isToLowercase) {
			url = url.toLowerCase();
		}
		return url;
	}

	/**
	 * 查看当前url和资源中的url是否匹配
	 * 
	 * @param isUseAntPath
	 * @param runningUrl
	 * @param rescUrl
	 * @return
	 */
	private boolean isResourceMatch(Boolean isUseAntPath, String runningUrl,
			String rescUrl) {
		if (isUseAntPath.booleanValue()) {
			return pathMatcher.match(rescUrl, runningUrl);
		} else {
			Pattern compiledPattern;
			Perl5Compiler compiler = new Perl5Compiler();
			try {
				// compiledPattern = compiler.compile(rescUrl,
				// Perl5Compiler.READ_ONLY_MASK);
				compiledPattern = compiler.compile(rescUrl,
						Perl5Compiler.READ_ONLY_MASK);
			} catch (MalformedPatternException mpe) {
				throw new IllegalArgumentException(
						"Malformed regular expression: " + rescUrl);
			}
			return matcher.matches(runningUrl, compiledPattern);
		}
	}

	// private void init(){
	// ConfigAttributeDefinition config = null;
	// String UserName =userCache.getUserNameFromCache("username");
	// //System.out.println("username=============="+UserName);
	// userDetails = (UserDetails) userCache
	// .getUserFromCache(UserName);
	// if (userDetails == null) {
	// return;
	// }
	// Resource [] resources = userDetails.getResources();
	//	     
	// // logger.info("-------------------- urls size1:"+resources);
	// for(int i=0;i<resources.length;i++){
	// if (resources[i].getResourceType().equals(Constants.ResourceType.URL)){
	// Resource resource = resources[i];
	// config = new ConfigAttributeDefinition();
	// for (int j = 0; j <resource.getRoles().length; j++) {
	//	        			
	// Role role = resource.getRoles()[j];
	// //logger.info("role.getRoleCode()role.getRoleCode()role.getRoleCode()"+role.getRoleCode());
	// String code = "";
	// if(code.equals(role.getRoleCode()))
	// continue;
	// SecurityConfig sc = new SecurityConfig(role.getRoleCode());
	// config.addConfigAttribute(sc);
	// code = role.getRoleCode();
	// }
	// addSecureUrl(resources[i].getResourceCode(),config);;
	// }
	// }
	// }



	public boolean isConvertUrlToLowercaseBeforeComparison() {
		return convertUrlToLowercaseBeforeComparison;
	}

	public void setConvertUrlToLowercaseBeforeComparison(
			boolean convertUrlToLowercaseBeforeComparison) {
		this.convertUrlToLowercaseBeforeComparison = convertUrlToLowercaseBeforeComparison;
	}

	public boolean isUseAntPath() {
		return useAntPath;
	}

	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	public PathMatcher getPathMatcher() {
		return pathMatcher;
	}

	public PatternMatcher getMatcher() {
		return matcher;
	}

	public boolean isProtectAllResource() {
		return protectAllResource;
	}

	public void setProtectAllResource(boolean protectAllResource) {
		this.protectAllResource = protectAllResource;
	}



	public ResourceCacheManager getResourceServiceManager() {
		return resourceServiceManager;
	}

	public void setResourceServiceManager(
			ResourceCacheManager resourceServiceManager) {
		this.resourceServiceManager = resourceServiceManager;
	}

}
