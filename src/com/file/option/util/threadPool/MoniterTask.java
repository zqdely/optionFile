package com.file.option.util.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.file.option.optionImpl.ReadFileParame;

public class MoniterTask implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(MoniterTask.class);
	
	private MoniterText moniterText;
	
	public MoniterTask(MoniterText moniterText){
		
		this.moniterText = moniterText;
		
	}
	
	@Override
	public void run() {
		
		//写处理流程
		
		String serviceName = "PLMC";
		
		String showName = ReadFileParame.fileValue("fileValue", "test.properties");
		
		
		

	}

}
