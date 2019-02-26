package org.tj.dubbo.common.base;

import java.io.Serializable;

/**
 * 查询条件基类
 * 
 * @ClassName: SeachPageModel
 * @Description: TODO
 * @author: 唐靖
 * @date: 2017年11月13日 下午5:09:57
 */
@SuppressWarnings("serial")
public class SeachPageModel implements Serializable {
	/**
	 * 页码，默认第一页
	 */
	protected int page = 1;
	/**
	 * 行数，默认一页十行
	 */
	protected int rows = 10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
