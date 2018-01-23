package com.file.option.dataCheck;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��ȡMD5ժҪ��Ϣ
 * @author Ally
 *
 */
public class MD5CheckMsg implements CheckInterface {
	
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
		
		return md5.toString();
	}

}