package org.tj.dubbo.common.model;

import java.io.Serializable;

/**
 * 消息对象
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

	/**
	 * 记录id
	 */
	private String id;

	/**
	 * 消息id
	 */
	private String messageid;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 消息体
	 */
	private String messagebody;

	/**
	 * 对应的队列名
	 */
	private String quenename;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessagebody() {
		return messagebody;
	}

	public void setMessagebody(String messagebody) {
		this.messagebody = messagebody;
	}

	public String getQuenename() {
		return quenename;
	}

	public void setQuenename(String quenename) {
		this.quenename = quenename;
	}

}
