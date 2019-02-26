package org.tj.dubbo.web.masterworker;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Master ma = new Master(new Worker(), 100);
		for (int i = 0; i < 100; i++) {
			Task t = new Task();
			t.setId(i + "");
			t.setPrice(new Random().nextInt(1000));
			ma.submit(t);
		}

		ma.execute();
		long start = System.currentTimeMillis();
		while (true) {
			if (ma.isComplete()) {
				System.out.println("一共耗时" + (System.currentTimeMillis() - start) + "毫秒，计算结果是" + ma.getResult());
				break;
			}
		}
	}
}
