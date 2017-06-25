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
 * ������ת����String����
 * @author lenovo
 *
 */
public class ObjectToString {
	
	private static Logger logger = LoggerFactory.getLogger(ObjectToString.class);
	
	/**
	 * ��clob����ת����String����
	 * @param msg ��ȡClob�������� 
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
				logger.info("��ȡreader����ʧ��");
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
				
				logger.info("��ȡcolbʧ��");
				
			}
			
			return sb.toString();
		}
		
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
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