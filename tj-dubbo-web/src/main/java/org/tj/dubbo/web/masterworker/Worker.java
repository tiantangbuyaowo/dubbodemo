package org.tj.dubbo.web.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 具体干活的工人
 * 
 * @author Administrator
 *
 */
public class Worker implements Runnable {

	/**
	 * 需要处理的线程
	 */
	private ConcurrentLinkedQueue<Task> taskQueue;

	/**
	 * 用户保存每个worker的工作结果
	 */
	private ConcurrentHashMap<String, Object> workResult;

	@Override
	public void run() {
		while (true) {
			Task task = taskQueue.poll();
			if (null == task) { // 为空就结束
				break;
			}

			// 执行操作
			handle(task);

		}
	}

	/**
	 * 执行计算的操作
	 * 
	 * @param task
	 */
	private void handle(Task task) {
		try {
			TimeUnit.MILLISECONDS.sleep(200); // 模拟处理事情需要的时间
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		workResult.put(task.getId(), task.getPrice());

	}

	public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void setWorkResult(ConcurrentHashMap<String, Object> workResult) {
		this.workResult = workResult;
	}

}
