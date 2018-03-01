package org.rapid.core;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Spring activeMQ 消息发送类
 * 
 * @author lynn
 */
public class SpringActiveMQSender {

	@Resource
	private JmsTemplate jmsTemplate;
	
	protected MessageCreator generateObjectCreator(Serializable message) {
		return new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage msg = session.createObjectMessage();
				msg.setObject(message);
				return msg;
			}
		};
	}
	
	protected MessageCreator generateTextCreator(String message) {
		return new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		};
	}
	
	protected void sendMessage(String queueName, MessageCreator creator) {
		Queue queue = new ActiveMQQueue(queueName);
		jmsTemplate.setDefaultDestination(queue);
		jmsTemplate.send(creator);
	}
}
