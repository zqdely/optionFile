package com.file.option.util.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建新线程
 * @author lenovo
 *
 */
public class ClientThreadPool extends ThreadPoolExecutor {
	
	public static ClientThreadPool instance;
	
	private static Logger logger = LoggerFactory.getLogger(ClientThreadPool.class);
	
	public ClientThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
		// TODO Auto-generated constructor stub
	}
	
	public static ClientThreadPool getInstance(){
		
		if(instance == null){
			
			synchronized (ClientThreadPool.class) {
				if(instance == null){
					
					instance = new ClientThreadPool(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.CallerRunsPolicy());
					
				}
				
			}
			
		}
		
		return instance;
		
	}
	
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	private final AtomicInteger numTasks = new AtomicInteger();
	
	private final AtomicInteger totalTime = new AtomicInteger();
	
	protected void beforeExecute(Thread t, Runnable r){
		
		super.beforeExecute(t, r);
		
		logger.info(String.format("Thread %s: start %s", t, r));
		
		startTime.set(System.nanoTime());
		
	}
	
	protected void afterExecute(Runnable r, Throwable t){
		
		try {
			
			long endTime = System.nanoTime();
			
			long taskTime = endTime - startTime.get();
			
			numTasks.incrementAndGet();
			
			//totalTime.addAndGet(taskTime);
			totalTime.addAndGet((int) taskTime);
			
			logger.info(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
			
		} finally{
			
			super.afterExecute(r, t);
			
		}
		
	}
	
	protected void terminated(){
		
		try{
			
			logger.info(String.format("Terminated: avg time=%dns", totalTime.get()/numTasks.get()));
			
		}finally{
			
			super.terminated();
			
		}
		
	}

}
