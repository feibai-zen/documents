package com.coding.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {

	@Test
	public void testTransaction() {
		//����Redis�Ŀͻ���
		Jedis client = new Jedis("192.168.79.11", 6379);
		
		//��������
		Transaction tc = null;
		try {
			tc = client.multi();
			//ת��
			tc.decrBy("tom", 100);
			tc.incrBy("mike", 100);
			//�ύ����
			tc.exec();
		}catch(Exception ex) {
			ex.printStackTrace();
			//�ع�����
			tc.discard();
		}
		
		client.close();
	}
	
	@Test
	public void testWatch() {
		Jedis client = new Jedis("192.168.79.11", 6379);
		//ģ����Ʊ
		
		//��ticket����
		client.watch("ticket");
		
		//��������
		Transaction tc =null;
		try {
			//��������
			tc = client.multi();
			//��Ǯ
			tc.decrBy("tom", 100);
			//Ʊ����һ
			tc.decr("ticket");
			
			Thread.sleep(8000); //˯8��
			
			//�ύ����
			tc.exec();
		}catch(Exception ex) {
			ex.printStackTrace();
			tc.discard();
		}
		client.close();
	}
}














