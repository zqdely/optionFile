package com.file.threadPool;

public class ExecuteThreadTask {
	
	public static void main(String[] args) {
		
		for(int i=0; i<5; i++){
			//�첽�߳�ִ��
			ClientThreadPool.getInstance().execute(new ClientEntityTask());
			
		}
		
	}

}
