package org.rembau.test.activeMQ;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Receiver {

    public static void main(String args[]) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:common.xml","classpath:spring-context-jms.xml");
        System.out.println("success");
    }
}
