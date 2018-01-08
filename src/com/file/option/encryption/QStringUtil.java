package com.file.option.encryption;

import java.util.Collection;
import java.util.Map;

public class QStringUtil {

	/*public static boolean isEmpty(Object parame){
		
		if(parame instanceof String){
			
			return !(parame==null && parame.equals(""));
			
		}else if(parame instanceof Map){
			
			return parame != null;
			
		}
		
		return true;
	}*/
	
	/**
	 * ÅÐ¿Õ·½·¨
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o){
		
		if(null == o){
			
			return true;
			
		}else if(o instanceof String && ((String) o).trim().length() == 0){
			
			return true;
			
		}else if(o instanceof Collection<?> && ((Collection<?>) o).size() == 0){
			
			return true;
			
		}else if(o instanceof Map<?, ?> && ((Map<?, ?>) o).size() == 0){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
}
