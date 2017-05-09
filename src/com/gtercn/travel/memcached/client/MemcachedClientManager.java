package com.gtercn.travel.memcached.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemcachedClientManager {
	private static final MemcachedClientManager instance = new MemcachedClientManager();
	private final static String XML_PATH="springXML/xmemcached_client.xml";
	private static final int storeTime = 5 * 60;

	// 待更新列表保存时效
	private static final String updateObjKey = "UpdateObj_";

	private static MemcachedClient memcachedClient;
	private static final long counterInitValue = 1;
	private static final long maxavilableValue = 10;

	private static final String UpdateObjCounter = "UpdateObjCounter";

	public static List<String> UpdateObjList = new ArrayList<String>(
			(int) maxavilableValue);

	static {
		for (long i = counterInitValue; i <= maxavilableValue; i++) {
			UpdateObjList.add(new StringBuffer(updateObjKey).append(i)
					.toString());
		}
	}

	private MemcachedClientManager() {
		ApplicationContext app = new ClassPathXmlApplicationContext(XML_PATH);
		memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
	}

	public static MemcachedClientManager getInstance() {
		return instance;
	}

	/**
	 * 封装delete方法
	 */
	public void delete(String arg0) {

		try {
			memcachedClient.deleteWithNoReply(arg0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 封装gets方法
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String arg0) {
		try {
			return (T) memcachedClient.get(arg0);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void replace(String arg0, int arg1, Object arg2) {
		try {
			memcachedClient.replaceWithNoReply(arg0, arg1, arg2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public void set(String arg0, int arg1, Object arg2) {
		try {
			memcachedClient.setWithNoReply(arg0, arg1, arg2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public <T> Map<String, GetsResponse<T>> gets(Collection<String> arg0) {
		try {
			return memcachedClient.gets(arg0);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计数器取值，支持分布式系统
	 */
	private long getCount(long min, long max, String key) {
		long count = min;
		Counter counter = memcachedClient.getCounter(key);
		try {
			count = counter.incrementAndGet();
			if (count > max) {
				counter.set(min);
				count = min;
			}
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 存入list_index-value
	 */
	public <T> void storeUpdateList(T obj) {
		try {
			long count = getCount(counterInitValue, maxavilableValue,
					UpdateObjCounter);
			String key = new StringBuffer(updateObjKey).append(count)
					.toString();
			memcachedClient.setWithNoReply(key, storeTime, obj);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据键集获取值集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getKeys(List<String> keylist) {
		List<T> rt = new ArrayList<T>();
		Map<String, GetsResponse<Object>> map = gets(keylist);
		if (null != map && map.size() > 0) {
			List<GetsResponse<Object>> list = new ArrayList<GetsResponse<Object>>(
					map.values());
			for (GetsResponse<Object> temp : list) {
				T t = (T) temp.getValue();
				rt.add(t);
			}
		}
		return rt;
	}
	
	
}
