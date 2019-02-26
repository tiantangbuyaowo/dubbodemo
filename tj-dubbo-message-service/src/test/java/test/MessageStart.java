package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageStart {
	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized (MessageStart.class) {
			while (true) {
				try {
					MessageStart.class.wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}
}
