package org.tj.dubbo.web.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * 
 * @author
 * @date 2016-6-20下午10:09:21
 * @param <T>
 */
public class Page<T> {
	/**
	 * 每页显示条数
	 */
	private int pageSize;
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页码
	 */
	private int pageNumber;
	/**
	 * 分页数据模型集合
	 */
	private List<T> rows = new ArrayList<T>();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotalPage() {
		this.totalPage = ((this.total + pageSize - 1) / pageSize);
		return totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
