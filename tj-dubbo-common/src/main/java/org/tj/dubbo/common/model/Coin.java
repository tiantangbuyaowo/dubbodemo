package org.tj.dubbo.common.model;

import java.io.Serializable;

/**
 * 金币对象
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Coin implements Serializable {

	private String id;

	private int num;

	private String type;

	private String userid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
