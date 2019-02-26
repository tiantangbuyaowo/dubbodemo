package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动消息服务
 * 
 * @author Administrator
 *
 */
public class MessageAppStart {
	private static final Log LOG = LogFactory.getLog(MessageAppStart.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "spring/spring-context.xml" });

			context.start();
			LOG.info("== context start");

		} catch (Exception e) {
			LOG.error("== application start error:", e);
			return;
		}

		synchronized (MessageAppStart.class) {
			while (true) {
				try {
					MessageAppStart.class.wait();
				} catch (InterruptedException e) {
					LOG.error("== synchronized error:", e);
				}
			}
		}
	}
}
