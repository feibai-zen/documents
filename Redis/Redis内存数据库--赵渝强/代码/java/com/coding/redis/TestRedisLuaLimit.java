package com.coding.redis;

public class TestRedisLuaLimit {

	public static void main(String[] args) {
		// ��������̣߳�ģ��߲����ĳ���
		for(int i=0;i<10;i++) {
			new Thread(new AccessApplication(i)).start();
		}
	}
}
