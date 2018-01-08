package com.file.option.encryption;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileEncryptInterface {
	
	/**
	 * 文件加密实现类
	 * @return 返回加密结果
	 */
	public boolean fileEncryptImpl(InputStream inputStream, String destFile, String createTime);
	
	/**
	 * 文件解密实现类
	 * @param destFile
	 * @return 返回解密結果
	 */
	public boolean fileDecryptImpl(OutputStream outputStream, String destFile, String createTime);

}
