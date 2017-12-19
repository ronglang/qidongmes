package com.css.common.web.syscommon.dao.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	public static int DEFAULT_PAGE_SIZE = 30;
	private int pagesize = DEFAULT_PAGE_SIZE;
	private int pageNo;
	@SuppressWarnings("rawtypes")
	@JsonProperty("Rows")
	private List data;
	@JsonProperty("Total")
	private long totalCount;
	private String time;
	
	/**
	 * ligerui datagrid 頁碼
	 */
	private int page;

	@SuppressWarnings("rawtypes")
	public Page() {
		this(1, 0L, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	@SuppressWarnings("rawtypes")
	public Page(int pageNo, long totalSize, int pageSize, List data) {
		this.pagesize = pageSize;
		this.pageNo = pageNo;
		this.totalCount = totalSize;
		this.data = data;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public long getTotalPageCount() {
		if (this.totalCount % this.pagesize == 0L) {
			long tmp = this.totalCount / this.pagesize;
			if (tmp == 0L) {
				return tmp + 1L;
			}
			return tmp;
		}
		return this.totalCount / this.pagesize + 1L;
	}

	public boolean hasNextPage() {
		return getPageNo() < getTotalPageCount() - 1L;
	}

	public boolean hasPreviousPage() {
		return getPageNo() > 1;
	}

	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	public static int getStartOfPage(int pageNo, int pageSize) {
		if (pageNo == 0) {
			return pageNo * pageSize;
		}
		return (pageNo - 1) * pageSize;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	@SuppressWarnings("rawtypes")
	public List getData() {
		return this.data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo == 0) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.pageNo = page;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
