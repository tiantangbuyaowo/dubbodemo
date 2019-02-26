package org.tj.dubbo.web.masterworker;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	/**
	 * 用户保存需要处理的任务
	 */
	private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();

	/**
	 * 用来保存需要执行任务的worker
	 */
	private HashMap<String, Thread> workers = new HashMap<String, Thread>();

	/**
	 * 用户保存每个worker的工作结果
	 */
	private ConcurrentHashMap<String, Object> workResult = new ConcurrentHashMap<String, Object>();

	public Master(Worker worker, int workCount) {
		worker.setTaskQueue(taskQueue);
		worker.setWorkResult(workResult);
		for (int i = 0; i < workCount; i++) {
			workers.put(Integer.toString(i), new Thread(worker));
		}
	}

	/**
	 * 用于提交任务
	 */
	public void submit(Task task) {
		taskQueue.add(task);
	}

	/**
	 * 开始执行任务
	 */
	public void execute() {
		for (String key : workers.keySet()) {
			workers.get(key).start(); // 执行线程
		}
	}

	/**
	 * 是否所有的线程都处理完成了
	 * 
	 * @return
	 */
	public boolean isComplete() {
		for (String key : workers.keySet()) {
			if (!workers.get(key).getState().equals(Thread.State.TERMINATED)) { // 有任何一个线程活着
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取计算结果
	 * 
	 * @return
	 */
	public int getResult() {
		int result = 0;
		for (String key : workResult.keySet()) {
			result = result + Integer.valueOf(workResult.get(key) + "");
		}
		return result;
	}

}
