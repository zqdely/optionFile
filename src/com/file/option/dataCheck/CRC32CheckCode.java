package com.file.option.dataCheck;

import java.util.zip.CRC32;

/**
 * 获取CRC32位校验码
 * @author Ally
 *
 */
public class CRC32CheckCode implements CheckInterface {

	@Override
	public String getCheckCode(Object dataMsg) {
		// TODO Auto-generated method stub
		//创建CRC32对象（java.util.zip.CRC32）
		CRC32 crc = new CRC32();
		
		crc.update(dataMsg.toString().getBytes());
		
		return String.valueOf(crc.getValue());
		
	}

}
