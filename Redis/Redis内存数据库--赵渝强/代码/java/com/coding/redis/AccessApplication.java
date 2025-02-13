package com.coding.redis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import redis.clients.jedis.Jedis;

//����ҵ�����
public class AccessApplication implements Runnable{

	private int clientID; //����ͻ��˵�ID
	public AccessApplication(int id) {
		this.clientID  = id;
	}
	
	@Override
	public void run() {
		// ����ҵ�񷽷�
		try {
			System.out.println(clientID + "�ſͻ����Ƿ�����ִ�в�����" + access());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean access() throws Exception{
		//����Redis�ͻ���
		Jedis client = new Jedis("192.168.79.11",6379);
		
		//��ȡLua�ļ�
		File luaFile = new File(TestRedisLuaLimit.class.getResource("/").toURI().getPath()+"limit.lua");
		//��ȡLua�ļ��е�����
		String luaScript = FileUtils.readFileToString(luaFile);
		
		//����������Key
		String key = "ip:" + System.currentTimeMillis()/1000; // ÿ������һ����һ����Key
		
		//���������Ĵ�С
		String limit = "4"; //ÿ��ֻ����3���ͻ��˽��з��ʡ�
		
		List<String> keys = new ArrayList<String>();
		keys.add(key);
		
		List<String> args = new ArrayList<String>();
		args.add(limit);
		
		//ִ��Lua�ű����������
		long result = (long) (client.eval(luaScript, keys, args));
		return result==1;
	}
}



















