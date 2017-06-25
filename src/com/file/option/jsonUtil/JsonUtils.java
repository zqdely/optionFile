package com.file.option.jsonUtil;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	private static final ObjectMapper objMap = new ObjectMapper();
	
	static{
		
		objMap.getSerializationConfig().setDateFormat(df);
		
	}
	
	
}
