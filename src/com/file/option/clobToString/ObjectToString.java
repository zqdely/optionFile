package com.file.option.clobToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将对象转换成String类型
 * @author lenovo
 *
 */
public class ObjectToString {
	
	private static Logger logger = LoggerFactory.getLogger(ObjectToString.class);
	
	/**
	 * 将clob类型转换成String类型
	 * @param msg 获取Clob类型数据 
	 * @return
	 */
	public static String clobToString(Clob msg){
		
		if(msg == null){
			return "";
		}else{
			
			String str = null;
			
			StringBuilder sb = null;
			
			Reader is = null;
			
			try {
				
				is = msg.getCharacterStream();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				logger.info("获取reader对象失败");
			}
			
			BufferedReader br = new BufferedReader(is);
			
			try {
				
				str = br.readLine();
				
				sb = new StringBuilder();
				
				while(str != null){
					
					sb.append(str);
					
					str = br.readLine();
					
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				logger.info("读取colb失败");
				
			}
			
			return sb.toString();
		}
		
	}
	
	/**
	 * 判断对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o){
		
		if(null == o){
			
			return true;
			
		}else if(o instanceof String && ((String)o).trim().length() == 0){
			
			return true;
			
		}else if(o instanceof Collection<?> && ((Collection<?>)o).size() == 0){
			
			return true;
			
		}else if(o instanceof Map<?, ?> && ((Map<?, ?>)o).size() == 0){
			
			return true;
			
		}else{
			
			return false;
		}
		
	}

}
