package com.file.option.netConnection;

import java.util.Map;

public interface NetConnectInterface {
	
	/**
	 * ����ʵ����
	 * @param connManager ���Ӷ���
	 * @param eventString ���Ӵ��������
	 * @return
	 */
	public Map<String, Object> sendData(HttpConnManager connManager, String eventString);

}