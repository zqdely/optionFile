package com.file.springConfig;

import java.util.Map;

import org.springframework.core.task.TaskExecutor;

public class SpringExecutor {
	
	private TaskExecutor executor;
	
	public void executorUse(Map map){
		//�߳���ʹ�õı����ǲ����̳е�
		final reqMap = map.get("mapKey");
		//��ȡbean����
		executor = (TaskExecutor)SystemFactoryBean.getBean("");
		
		executor.execute(new Runnable() {
			//@Override
			public void run() {
				// TODO Auto-generated method stub
				//��Ҫ�첽�����ĳ���
				
			}
		});
		
	}

}