package com.file.option.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD {

	public static final String KEY_MD = "MD"; 
	  public static String getResult(String inputStr)
	  {
	    System.out.println("=======����ǰ������:"+inputStr);
	    BigInteger bigInteger=null;
	    try {
	     MessageDigest md = MessageDigest.getInstance(KEY_MD); 
	     byte[] inputData = inputStr.getBytes();
	     md.update(inputData); 
	     bigInteger = new BigInteger(md.digest()); 
	    } catch (Exception e) {e.printStackTrace();}
	    System.out.println("MD���ܺ�:" + bigInteger.toString()); 
	    return bigInteger.toString();
	  }
	  
	  
	  public static void main(String args[])
	  {
	    try {
	       String inputStr = "�򵥼���"; 
	       getResult(inputStr);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}