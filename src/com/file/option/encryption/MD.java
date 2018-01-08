package com.file.option.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD {

	public static final String KEY_MD = "MD"; 
	  public static String getResult(String inputStr)
	  {
	    System.out.println("=======加密前的数据:"+inputStr);
	    BigInteger bigInteger=null;
	    try {
	     MessageDigest md = MessageDigest.getInstance(KEY_MD); 
	     byte[] inputData = inputStr.getBytes();
	     md.update(inputData); 
	     bigInteger = new BigInteger(md.digest()); 
	    } catch (Exception e) {e.printStackTrace();}
	    System.out.println("MD加密后:" + bigInteger.toString()); 
	    return bigInteger.toString();
	  }
	  
	  
	  public static void main(String args[])
	  {
	       String inputStr = "简单加密"; 
	       getResult(inputStr);
	  }
	
}
