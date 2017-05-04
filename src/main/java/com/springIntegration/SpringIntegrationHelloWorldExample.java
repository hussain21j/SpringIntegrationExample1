package com.springIntegration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class SpringIntegrationHelloWorldExample {
	private MessageChannel inChannel;
	 
    public static void main(String[] args) {
    	System.out.println("11111111111111");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        try {
            SpringIntegrationHelloWorldExample springIntExample = (SpringIntegrationHelloWorldExample) context
                    .getBean("springIntHelloWorldExample");
            springIntExample.post("This is spring integration example.");
            QueueChannel outChannel = (QueueChannel) context.getBean("outputChannel");
            System.out.println(outChannel.receive());
        } finally {
            context.close();
        }
        
        System.out.println("222222222222");
    }
 
    public void post(String payload) {
        Message message = MessageBuilder.withPayload(payload).build();
        inChannel.send(message);
    }
 
    public void setInputChannel(MessageChannel in) {
        this.inChannel = in;
    }
}
