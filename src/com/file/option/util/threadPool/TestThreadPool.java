package com.file.option.util.threadPool;

public class TestThreadPool {
	
	public static void main(String[] args) {
		
		MoniterText moniterText = new MoniterText();
		
		moniterText.setName("ppp");
		
		moniterText.setPassWord("1232");
		
		ClientThreadPool.getInstance().execute(new MoniterTask(moniterText));
		
	}

}
