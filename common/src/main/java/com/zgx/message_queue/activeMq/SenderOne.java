package com.zgx.message_queue.activeMq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SenderOne {
    private JmsTemplate jmsTemplateOne;

    public JmsTemplate getJmsTemplateOne() {
        return jmsTemplateOne;
    }

    public void setJmsTemplateOne(JmsTemplate jmsTemplateOne) {
        this.jmsTemplateOne = jmsTemplateOne;
    }

    //测试的
    public void sendInfo(final String messageRecord,final String flag){
        jmsTemplateOne.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                return message;
            }
        });
    }
}
