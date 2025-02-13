package com.coding.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class TestRedisDemo {

	//ʹ����ͨ�Ĳ�����Redis�в���10000������
	@Test
	public void testNormalCommand() {
		Jedis client = new Jedis("192.168.79.11", 6379);
		
		//��¼������ʼʱ��
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			client.set("key"+i, "value"+i);
		}
		
		//��¼����������ʱ��
		long end = System.currentTimeMillis();
		
		System.out.println("��ͨ��ʽִ�е�ʱ�䣺" + (end-start));
		
		client.close();
	}
	
	@Test
	public void testPipeLineCommand() {
		Jedis client = new Jedis("192.168.79.11", 6379);
		//�����ܵ�
		Pipeline pl = client.pipelined();
		
		//��¼������ʼʱ��
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			pl.set("pipelinekey"+i, "pipelinevalue"+i);
		}
		
		pl.sync();
		
		//��¼����������ʱ��
		long end = System.currentTimeMillis();
		
		System.out.println("�ܵ���ʽִ�е�ʱ�䣺" + (end-start));
		
		client.close();		
	}
}































