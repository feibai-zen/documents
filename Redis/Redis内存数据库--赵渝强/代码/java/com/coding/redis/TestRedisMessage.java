package com.coding.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class TestRedisMessage {

	public static void main(String[] args) {
		Jedis client = new Jedis("192.168.79.11", 6379);

		client.subscribe(new MyListener(), "mychannel");
		
		client.psubscribe(new MyListener(), "c*");
	}
	
}

class MyListener extends JedisPubSub{

	@Override
	public void onMessage(String channel, String message) {
		System.out.println("Ƶ����" +channel + "  ���յ�����Ϣ��" +message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("ģʽ��" + pattern+"Ƶ����" +channel + "  ���յ�����Ϣ��" +message);
	}
}










