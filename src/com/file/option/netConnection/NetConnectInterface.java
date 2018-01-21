package com.file.option.netConnection;

import java.util.Map;

public interface NetConnectInterface {
	
	/**
	 * 连接实现类
	 * @param connManager 连接对象
	 * @param eventString 连接传输的数据
	 * @return
	 */
	public Map<String, Object> sendData(HttpConnManager connManager, String eventString);

}
