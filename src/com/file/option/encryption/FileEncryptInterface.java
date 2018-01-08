package com.file.option.encryption;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileEncryptInterface {
	
	/**
	 * �ļ�����ʵ����
	 * @return ���ؼ��ܽ��
	 */
	public boolean fileEncryptImpl(InputStream inputStream, String destFile, String createTime);
	
	/**
	 * �ļ�����ʵ����
	 * @param destFile
	 * @return ���ؽ��ܽY��
	 */
	public boolean fileDecryptImpl(OutputStream outputStream, String destFile, String createTime);

}