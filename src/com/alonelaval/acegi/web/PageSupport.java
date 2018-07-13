package com.alonelaval.acegi.web;

import java.util.List;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.web.PageSupport.java
 * @createDate 2009-6-10 上午11:22:32	
 */
public class PageSupport <T> {

	private int startIndex;
	private int pageSize;
	private int totalCount;
	private List<T> items;
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
