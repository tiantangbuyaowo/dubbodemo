package org.tj.dubbo.web.masterworker;

/**
 * 需要执行的任务
 * 
 * @author Administrator
 *
 */
public class Task {

	private String id;

	private int price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
