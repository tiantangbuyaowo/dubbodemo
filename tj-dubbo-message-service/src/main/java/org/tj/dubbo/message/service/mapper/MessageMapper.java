package org.tj.dubbo.message.service.mapper;

import org.tj.dubbo.common.model.Message;

/**
 * 消息操作mapper
 * 
 * @author Administrator
 *
 */
public interface MessageMapper {

	/**
	 * 插入待发送的消息
	 * 
	 * @param message
	 */
	void addConfirmMessage(Message message);

	/**
	 * 根据id查询待发送消息
	 * 
	 * @param id
	 * @return
	 */
	Message getConfirmMessageById(String id);

	/**
	 * 删除消息
	 * 
	 * @param id
	 */
	void deleteSendIngMessage(String id);

	/**
	 * 改变消息状态为发送中
	 * 
	 * @param m
	 */
	void updateMessageToSendIng(Message m);

}
