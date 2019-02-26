package org.tj.dubbo.message.service.impl;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.tj.dubbo.common.model.Message;
import org.tj.dubbo.common.util.JacksonUtil;
import org.tj.dubbo.message.service.MessageService;
import org.tj.dubbo.message.service.mapper.MessageMapper;

/**
 * 消息服务service
 * 
 * @author Administrator
 *
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageMapper messageMapper;
	@Resource
	private JmsTemplate notifyJmsTemplate;

	/**
	 * 插入待发送的消息
	 */
	public void addConfirmMessage(Message message) {
		messageMapper.addConfirmMessage(message);

	}

	@Override
	public void sendConfirmMessage(Message message) throws Exception {
		final Message m = messageMapper.getConfirmMessageById(message.getId());
		if (null != m) {
			// m.setStatus("1");
			messageMapper.updateMessageToSendIng(m);
			notifyJmsTemplate.setDefaultDestinationName(m.getQuenename());
			notifyJmsTemplate.send(new MessageCreator() {

				@Override
				public javax.jms.Message createMessage(Session session) throws JMSException {

					return session.createTextMessage(JacksonUtil.toJSon(m));
				}

			});
		}
	}

	/**
	 * 删除消息
	 */
	public void deleteSendIngMessage(Message message) {
		messageMapper.deleteSendIngMessage(message.getId());
	}

}
