package org.tj.dubbo.web.base;

/**
 * 分页查询条件的父类
 * @author 唐靖
 * @date 2017年3月4日下午7:41:02
 */
public class BasePageSearch {
	/**
	 * 每页显示条数
	 */
	protected int pageSize = 20;
	/**
	 * 页码 默认为1
	 */
	protected int pageNumber = 1;
	/**
	 * 开始条数
	 */
	protected int start;

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getStart() {
		this.start = (pageNumber - 1) * pageSize;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
