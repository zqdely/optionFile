package com.file.springConfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import cn.bdqn.power.util.FactoryBean;

public class SystemFactoryBean {
	
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		
		FactoryBean.applicationContext = applicationContext;
		
	}
	
	/**
	 * ����Id��ȡbean����
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId){
		
		String thread = "executor";
		//��ȡ�߳�bean
		return applicationContext.getBean(thread);
		
	}

}