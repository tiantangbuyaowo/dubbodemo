package org.tj.dubbo.message.service;

import org.tj.dubbo.common.model.Message;

/**
 * 消息操作service
 * 
 * @author Administrator
 *
 */
public interface MessageService {

	/**
	 * 添加待发送消息
	 * 
	 * @param message
	 */
	public void addConfirmMessage(Message message);

	/**
	 * 发送待确认的消息
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void sendConfirmMessage(Message message) throws Exception;

	/**
	 * 删除发送中的消息
	 * 
	 * @param message
	 */
	public void deleteSendIngMessage(Message message);

}
