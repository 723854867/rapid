package org.rapid.core.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.activemq.ScheduledMessage;
import org.rapid.core.condition.MQProviderCondition;
import org.rapid.util.StringUtil;
import org.springframework.context.annotation.Conditional;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

@Component
@Conditional(MQProviderCondition.class)
public class SchedulerMessageProcessor implements MessagePostProcessor {

	@Override
	public Message postProcessMessage(Message message) throws JMSException {
		SchedulerMessage schedulerMessage = (SchedulerMessage) ((ObjectMessage) message).getObject();
		((ObjectMessage) message).setObject(schedulerMessage.getAttach());
		if (null != schedulerMessage.getDelay())
			message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, schedulerMessage.getDelay());
		if (null != schedulerMessage.getRepeat()) {
			message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, schedulerMessage.getRepeat());
			message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, schedulerMessage.getPeriod());
		}
		if (StringUtil.hasText(schedulerMessage.getCron())) 
			message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, schedulerMessage.getCron());
		return message;
	}
}
