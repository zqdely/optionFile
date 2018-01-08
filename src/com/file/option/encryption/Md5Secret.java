package com.file.option.encryption;


import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5加密实现类
 * @author Ally
 * @version 1.0
 *
 */
public class Md5Secret implements EncodeEncryptInterface { 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * MD5加码 生成32位md5码(李迎)
	 * @param inStr
	 * @return
	 */
    private String string2MD5(String inStr){
    	
        MessageDigest md5 = null;
        
        try{
        	
            md5 = MessageDigest.getInstance(EncodeConstants.KEY_MD5);
            
        }catch (Exception e){  
            
        	logger.info("加密失败，原因" + e.toString());
        	
            e.printStackTrace();
            
            return "";  
        }  
        
        //将字符串转换成响应的字符数组
        char[] charArray = inStr.toCharArray();
        
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)
        	
            byteArray[i] = (byte) charArray[i];
        
        byte[] md5Bytes = md5.digest(byteArray);
        
        StringBuffer hexValue = new StringBuffer();
        
        for (int i = 0; i < md5Bytes.length; i++){
        	
            int val = ((int) md5Bytes[i]) & 0xff;
            
            if (val < 16)
            	
                hexValue.append("0");
            
            //toHexString(int i) 以十六进制的无符号整数形式返回一个整数参数的字符串表示形式
            hexValue.append(Integer.toHexString(val));  
            
        }  
        
        return hexValue.toString();
        
    }  
    
    
    public static void main(String[] args) {
		
    	String str1 = "张丽";
		
    	Md5Secret md5 = new Md5Secret();
    	
		System.out.println(md5.string2MD5(str1));
    	
	}


	@Override
	public String encodeEncrypt(String parameStr) {
		// TODO Auto-generated method stub
		return string2MD5(parameStr);
	}
}
