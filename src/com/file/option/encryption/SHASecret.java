package com.file.option.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SHA加密实现类
 * @author Ally
 * @version 1.0
 *
 */
public class SHASecret implements EncodeEncryptInterface{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * SHA加密实现方法(生成纯数字)
	 * @param inputStr
	 * @return encodeEncryptResult
	 */
	private String getEncryptResult(String inputStr){
		
		BigInteger encodeString =null;
	    
		logger.info("=======加密前的数据:" + inputStr);
		
	    byte[] inputData = inputStr.getBytes(); 
		
	    try {
	    	
			MessageDigest messageDigest = MessageDigest.getInstance(EncodeConstants.KEY_SHA);
			
			messageDigest.update(inputData);
			
			encodeString = new BigInteger(messageDigest.digest());
			
			logger.info("SHA加密后:" + encodeString.toString());
			
		} catch (NoSuchAlgorithmException e) {
			
			logger.info("SHA加密失败" + e.toString());
			
			return "";
		} 
	    
	    return encodeString.toString();
	}

	 /*** 
     * SHA加密 生成40位SHA码
     * @param 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) { 
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
	
	public static void main(String[] args) throws Exception {
		String Str = "111";
		
		SHASecret sha = new SHASecret();
		
		System.out.println("结果"+sha.shaEncode(Str));
	}
	
	@Override
	public String encodeEncrypt(String parameStr) {
		// TODO Auto-generated method stub
		return getEncryptResult(parameStr);
	}

}
