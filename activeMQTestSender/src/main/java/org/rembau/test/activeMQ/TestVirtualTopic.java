package org.rembau.test.activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestVirtualTopic {
  
    public static void main(String[] args) {  
        try {  
  
            ActiveMQConnectionFactory factoryA = new ActiveMQConnectionFactory(  
                    "tcp://127.0.0.1:61616");  
  
            Queue queue = new ActiveMQQueue(getVirtualTopicConsumerNameA());
            System.out.println(queue.toString());
            ActiveMQConnection conn = (ActiveMQConnection) factoryA.createConnection();
            conn.start();  
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);  
  
            MessageConsumer consumer1 = session.createConsumer( queue );  
            MessageConsumer consumer2 = session.createConsumer( queue );
            ActiveMQQueue destination = new ActiveMQQueue(getVirtualTopicConsumerNameB());
            System.out.println(destination.toString());

            MessageConsumer consumer3 = session.createConsumer(destination);
            final AtomicInteger aint1 = new AtomicInteger(0);  
            MessageListener listenerA = new MessageListener() {  
                public void onMessage(Message message) {  
                    try {  
                        System.out.println(aint1.incrementAndGet()  
                                + " => receive from "+ getVirtualTopicConsumerNameA() +": " + message);  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            };  
            consumer1.setMessageListener(listenerA);  
            consumer2.setMessageListener(listenerA);  
            final AtomicInteger aint2 = new AtomicInteger(0);  
            MessageListener listenerB = new MessageListener() {  
                public void onMessage(Message message) {  
                    try {  
                        System.out.println(aint2.incrementAndGet()  
                                + " => receive from "+ getVirtualTopicConsumerNameB() +": " + message);  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            };  
            consumer3.setMessageListener(listenerB);

            ActiveMQTopic mqTopic = new ActiveMQTopic(getVirtualTopicName());
            System.out.println(mqTopic.toString());
            MessageProducer producer = session.createProducer(mqTopic);
            int index = 0;  
            while (index++ < 100) {  
                TextMessage message = session.createTextMessage(index  
                        + " message.");  
                producer.send(message);  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
      protected static String getVirtualTopicName() {  
            return "VirtualTopic.TEST";  
        }  
  
        protected static String getVirtualTopicConsumerNameA() {  
            return "Consumer.A.VirtualTopic.TEST";  
        }  
          
        protected static String getVirtualTopicConsumerNameB() {  
            return "Consumer.B.VirtualTopic.TEST";  
        }  
  
}  