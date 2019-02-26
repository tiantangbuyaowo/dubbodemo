package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserStart {
	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized (UserStart.class) {
			while (true) {
				try {
					UserStart.class.wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}
}
