package com.file.threadPool;

import java.util.concurrent.TimeUnit;

public class ClientEntityTask implements Runnable {

	@Override
	public void run() {
		// �˴�д��Ҫʹ���̳߳�ִ�е�����
		try {
			//���߷���
			TimeUnit.MINUTES.sleep(1);
			//����2s
			TimeUnit.SECONDS.sleep(20);
			
			System.out.println("����ִ�е�����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}