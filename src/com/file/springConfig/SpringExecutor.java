package com.file.springConfig;

import java.util.Map;

import org.springframework.core.task.TaskExecutor;

public class SpringExecutor {
	
	private TaskExecutor executor;
	
	public void executorUse(Map map){
		//线程内使用的必须是不被继承的
		final reqMap = map.get("mapKey");
		//获取bean对象
		executor = (TaskExecutor)SystemFactoryBean.getBean("");
		
		executor.execute(new Runnable() {
			//@Override
			public void run() {
				// TODO Auto-generated method stub
				//需要异步处理的程序
				
			}
		});
		
	}

}
