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
	 * 根据Id获取bean对象
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId){
		
		String thread = "executor";
		//获取线程bean
		return applicationContext.getBean(thread);
		
	}

}
