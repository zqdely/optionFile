package com.file.threadPool;

import java.util.concurrent.TimeUnit;

public class ClientEntityTask implements Runnable {

	@Override
	public void run() {
		// 此处写需要使用线程池执行的任务
		try {
			//休眠分钟
			TimeUnit.MINUTES.sleep(1);
			//休眠2s
			TimeUnit.SECONDS.sleep(20);
			
			System.out.println("这是执行的任务");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
