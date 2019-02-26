package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CoinStart {
	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized (CoinStart.class) {
			while (true) {
				try {
					CoinStart.class.wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}
}
