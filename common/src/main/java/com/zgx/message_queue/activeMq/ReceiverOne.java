package com.zgx.message_queue.activeMq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ReceiverOne implements MessageListener {
    //测试方法
    @Override
    public void onMessage(Message message) {
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            int i = Integer.parseInt(text);
            System.out.println("消息: ==="+i);
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("异常消息:===: "+e.getMessage());
        }


    }
}
