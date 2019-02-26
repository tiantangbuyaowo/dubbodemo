package org.tj.dubbo.common.exception;

/**
 * 自定义异常
 * @ClassName: RepairException
 * @Description: TODO
 * @author: 唐靖
 * @date: 2017年7月24日 下午3:32:56
 */
public class DubboException extends RuntimeException {
	private static final long serialVersionUID = -1541896804859211367L;

	public DubboException(String message) {
		super(message);
	}
}
