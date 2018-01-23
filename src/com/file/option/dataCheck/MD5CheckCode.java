package com.file.option.dataCheck;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取MD5校验码
 * @author Ally
 *
 */
public class MD5CheckCode implements CheckInterface {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String getCheckCode(Object dataMsg) {
		//信息摘要算法对象生成异常
		MessageDigest md5 = null;
		 
		try {
			
			//创建具有指定算法名称的信息摘要  如： MD5 或 SHA 算法
			md5 = MessageDigest.getInstance("MD5");
			
			//使用指定的字节更新摘要
			md5.update(dataMsg.toString().getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			
			logger.info("MD5信息摘要算法对象生成异常",e);
			
		}
		
		//md5.digest()完成摘要计算   abs()返回其值是此 BigInteger 的绝对值的
		return new BigInteger(md5.digest()).abs().toString();
	}
	
	public static void main(String[] args) {
		
		CheckInterface check = new MD5CheckCode();
		
		System.out.println(check.getCheckCode("测试报文"));
		
	}
	
}
