package com.file.option.optionImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadFileParame{
	private static Properties properties = null;
	
	/*static{
		//保证在流中或者从流中加载表示一个持久的属性集
		properties = new Properties();
		
		InputStream is = null;
		
		try {
			
			is = getURL("test.properties").openStream();
			//从输入流中读取键值对
			properties.load(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				
				try {
					
					is.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}*/
	
	/**
	 * 获取配置文件中属性名对应的值
	 * @param name 参数：属性名
	 * @param filePath 参数：配置文件位置src下边的路径
	 * @return String类型的属性值
	 */
	
	public static String fileValue(String name, String filePath){

		//保证在流中或者从流中加载表示一个持久的属性集
		properties = new Properties();
		
		InputStream is = null;
		
		try {
			
			is = getURL(filePath).openStream();
			//从输入流中读取键值对
			properties.load(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				
				try {
					
					is.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		return properties.getProperty(name);
	}
	/**
	 * 类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询。
	 * @param FilePath
	 * @return
	 */
	public static URL getURL(String FilePath){
		URL url = null;
		//上下文 ClassLoader 由线程创建者提供，供运行于该线程中的代码在加载类和资源时使用。如果未设定，则默认为父线程的 ClassLoader 上下文。
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		//查找具有给定名称的资源。类加载器实现应该重写此方法，以指定从何处查找资源
		url = cl.getResource(FilePath);
		
		if(url == null){
			
			url = ClassLoader.getSystemResource(FilePath);
					
		}
		
		if (null == url){
			
			try {
				
				File file = new File(FilePath);
				
				//查看是否存在
				if(file.exists()){
					url = file.toURI().toURL();
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(url == null){
			/******
			 * *待扩展*
			 * ******
			 * */
			
			
		}
		return url;
	} 
	
	
	/**
	 * 入口函数
	 * @param keyName
	 * @return
	 */
	/*public static String getProp(String keyName){
		
		return properties.getProperty(keyName);
		
	}*/
	
	
	/*public static void main(String[] args) {

		String info = ReadFileParame.getProp("showName");
		System.out.println(info);
		
	}*/
}
