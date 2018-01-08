package com.file.option.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SHA����ʵ����
 * @author Ally
 * @version 1.0
 *
 */
public class SHASecret implements EncodeEncryptInterface{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * SHA����ʵ�ַ���(���ɴ�����)
	 * @param inputStr
	 * @return encodeEncryptResult
	 */
	private String getEncryptResult(String inputStr){
		
		BigInteger encodeString =null;
	    
		logger.info("=======����ǰ������:" + inputStr);
		
	    byte[] inputData = inputStr.getBytes(); 
		
	    try {
	    	
			MessageDigest messageDigest = MessageDigest.getInstance(EncodeConstants.KEY_SHA);
			
			messageDigest.update(inputData);
			
			encodeString = new BigInteger(messageDigest.digest());
			
			logger.info("SHA���ܺ�:" + encodeString.toString());
			
		} catch (NoSuchAlgorithmException e) {
			
			logger.info("SHA����ʧ��" + e.toString());
			
			return "";
		} 
	    
	    return encodeString.toString();
	}

	 /*** 
     * SHA���� ����40λSHA��
     * @param �������ַ���
     * @return ����40λSHA��
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
		
		System.out.println("���"+sha.shaEncode(Str));
	}
	
	@Override
	public String encodeEncrypt(String parameStr) {
		// TODO Auto-generated method stub
		return getEncryptResult(parameStr);
	}

}