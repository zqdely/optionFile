package com.file.option.netConnection;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RennameRunable {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AtomicInteger c = new AtomicInteger();
	
	private ThreadPoolExecutor executor;
	
	private String name = null;
	
	public void exec(Runnable runnable, String name){
		
		try {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
			
			String time = df.format(new Date());
			
			StringBuffer sb  = new StringBuffer();
			
			sb.append(InetAddress.getLocalHost()).append("系统唯一标识名").append(c.incrementAndGet()).append("-").append(time);
			
			name = sb.toString();
			
		} catch (UnknownHostException e) {
			
			logger.error("ASRA重命名线程名错误",e);
			
		}
		
		Runnable newRunable = new thread
		
	}

}
