package com.file.option.dataCheck;

public interface CheckInterface {
	
	/**
	 * ������������У���루��ȡCRC32/MD5У���룩
	 * 		ע�⣺MD5Ĭ�Ϸ���38λ����Ҫ�Լ���ȡ
	 * 			 CRC32Ĭ�Ϸ���10λ
	 * @param dataMsg ��Ҫ����У���������
	 * @return ����У������
	 */
	public String getCheckCode(Object dataMsg);

}