package org.rembau.test.activeMQ;

import com.unilife.commons.jms.JmsMessage;
import com.unilife.commons.jms.JmsSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Sender {

    public static void main(String args[]) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:common.xml","classpath:spring-context-jms.xml");

        JmsSender sender = classPathXmlApplicationContext.getBean(JmsSender.class);

        JmsMessage jmsMessage = new JmsMessage(null, null, "test");

        sender.sendMessage(jmsMessage);
        System.out.println("success");
    }
}
