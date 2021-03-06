package org.tj.dubbo.common.model;

import java.io.Serializable;

/**
 * User对象
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private String id;

	private String name;

	private String password;

	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
