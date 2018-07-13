package com.alonelaval.acegi.test;

import java.util.HashMap;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.test.TestInterfaceImpl.java
 * @createDate 2009-6-16 下午02:10:51	
 */
public class TestInterfaceImpl implements TestInterface {
	protected static Logger logger = LoggerFactory.getLogger(TestInterfaceImpl.class);

	

	public static final Map<String,String> data = new HashMap<String, String>();
	static{
		data.put("huawei","huawei");
		data.put("java", "java");
		data.put("test","test");
	}
	
	public void Add(String name, String pwd) {
		data.put(name,pwd);

	}

	public void get(String name) {
		for (Entry<String, String> entry : data.entrySet()) {
			if(entry.getValue().equals(name)){
				logger.info("get by username .......................");
			}
		}
	}

	public void getAll() {
		logger.info("getALll by username .......................");

	}

	public void remove(String name) {
		logger.info("remove by username .......................");

	}

	public void update(String name, String pwd) {
		logger.info("update by username .......................");

	}

}
