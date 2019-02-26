package org.tj.dubbo.common.quene;

/**
 * 消息队列
 * 
 * @author Administrator
 *
 */
public enum MessageQuene {

	REGISTER_USER_QUENE("新用户注册队列", "REGISTER_USER_QUENE");

	/**
	 * 队列名称
	 */
	private String quene;
	/** 队列描述 */
	private String desc;

	private MessageQuene(String desc, String quene) {
		this.quene = quene;
		this.desc = desc;
	}

	public String getQuene() {
		return quene;
	}

	public void setQuene(String quene) {
		this.quene = quene;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
