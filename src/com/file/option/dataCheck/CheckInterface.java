package com.file.option.dataCheck;

public interface CheckInterface {
	
	/**
	 * 根据数据生成校验码（获取CRC32/MD5校验码）
	 * 		注意：MD5默认返回38位，需要自己截取
	 * 			 CRC32默认返回10位
	 * @param dataMsg 需要生成校验码的数据
	 * @return 返回校验数据
	 */
	public String getCheckCode(Object dataMsg);

}
