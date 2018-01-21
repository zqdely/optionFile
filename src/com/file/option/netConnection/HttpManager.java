package com.file.option.netConnection;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpManager implements NetConnectInterface {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> sendData(HttpConnManager connManager, String eventString) {

		URLConnection con = null;
		
		OutputStream os = null;
		
		InputStream is = null;
		
		PrintWriter pw = null;
		
		if(!(eventString == null || "".equals(eventString))){
			
			try {
				
				StringBuffer sb = new StringBuffer(NetConstants.DATA_JSON_HREDA + URLEncoder.encode(eventString, NetConstants.DATA_CHANNSET) + NetConstants.DATA_JSON_BODY);
				
				con = connManager.getConnection();
				
				os = con.getOutputStream();
				
				pw = new PrintWriter(os);
				
				pw.write(new String((sb.toString()).getBytes(NetConstants.DATA_CHANNSET)));
				
				pw.flush();
				
				os.flush();
				
				is = con.getInputStream();
				
				byte[] rspByte = new byte[is.available()];
				
				is.read(rspByte);
				
				String rspString = new String(rspByte, NetConstants.DATA_CHANNSET).trim();
				
				ObjectMapper jsonMap = new ObjectMapper();
				
				return jsonMap.readValue(rspString, Map.class);
				
			} catch (Exception e) {
				
				logger.info("获取http响应数据失败" + e.toString());
				
			}finally{
				
				try {
					
					if(is != null){
						
						is.close();
						
					}
					if(pw != null){
						
						pw.close();
						
					}
					if(os != null){
						
						os.close();
						
					}
					
				} catch (Exception e2) {
					
					logger.info("流关闭失败");
					
					if(is != null) is = null;
					
					if(pw != null) pw = null;
					
					if(os != null) os = null;
					
				}
				
			}
			
		}
		
		return null;
		
	}

}
