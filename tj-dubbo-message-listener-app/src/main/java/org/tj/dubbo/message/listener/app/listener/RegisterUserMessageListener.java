package org.tj.dubbo.message.listener.app.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.tj.dubbo.coin.service.CoinService;
import org.tj.dubbo.common.model.User;
import org.tj.dubbo.common.util.JacksonUtil;
import org.tj.dubbo.message.service.MessageService;

/**
 * 队列消息监听器
 * 
 * @author Administrator
 *
 */
public class RegisterUserMessageListener implements SessionAwareMessageListener<Message> {

	/**
	 * 积分操作服务
	 */
	private CoinService coinService;

	/**
	 * 消息服务service
	 */
	private MessageService messageService;

	/**
	 * 消息处理
	 */
	public void onMessage(Message message, Session session) throws JMSException {

		ActiveMQTextMessage objectMessage = (ActiveMQTextMessage) message;
		String strMessage = objectMessage.getText();
		org.tj.dubbo.common.model.Message userMessage = JacksonUtil.readValue(strMessage,
				org.tj.dubbo.common.model.Message.class);
		User user = JacksonUtil.readValue(userMessage.getMessagebody(), User.class);
		coinService.registerUserAddCoin(user.getId()); // 为用户添加积分
		messageService.deleteSendIngMessage(userMessage); // 删掉发送中的消息

	}

	public CoinService getCoinService() {
		return coinService;
	}

	public void setCoinService(CoinService coinService) {
		this.coinService = coinService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
