package com.file.option.dataCheck;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��ȡMD5У����
 * @author Ally
 *
 */
public class MD5CheckCode implements CheckInterface {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String getCheckCode(Object dataMsg) {
		//��ϢժҪ�㷨���������쳣
		MessageDigest md5 = null;
		 
		try {
			
			//��������ָ���㷨���Ƶ���ϢժҪ  �磺 MD5 �� SHA �㷨
			md5 = MessageDigest.getInstance("MD5");
			
			//ʹ��ָ�����ֽڸ���ժҪ
			md5.update(dataMsg.toString().getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			
			logger.info("MD5��ϢժҪ�㷨���������쳣",e);
			
		}
		
		//md5.digest()���ժҪ����
		return new BigInteger(md5.digest()).toString();
	}
	
}