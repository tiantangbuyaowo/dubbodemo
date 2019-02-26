package org.tj.dubbo.common.util;

/**
 * ajax异步请求时的默认返回对象
 * 
 * @author 唐靖
 * @date 2015年12月11日下午3:29:37
 */
public class HttpResult {
	/**
	 * 默认操作失败
	 */
	protected String resultCode = Constants.RESULT_FINAL;
	/**
	 * 操作描述
	 */
	protected String desc = "服务器忙，请稍后再试";
	/**
	 * 一些需要在页面显示的数据
	 */
	protected Object obj;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
