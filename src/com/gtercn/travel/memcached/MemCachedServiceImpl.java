package com.gtercn.travel.memcached;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;

//@Service(value="memcachedService")
public class MemCachedServiceImpl implements MemCachedService {
	
	@Autowired
	private MemCachedClient memcachedClient;

	public boolean delete(String key) {
		return memcachedClient.delete(key);
	}

	public Object getDataByCache(String key) {
		return  memcachedClient.get(key);
	}

	public Object[] getDataByCache(String[] keys) {
		return memcachedClient.getMultiArray(keys);
	}

	public boolean save(String key, Object object) {
		return memcachedClient.set(key, object);
	}

	public boolean save(String key, Object object, Date deadline) {
		return memcachedClient.set(key, object, deadline);
	}

	public boolean save(String key, Object object, long deadline) {
		return memcachedClient.set(key, object, new Date(System.currentTimeMillis() + deadline));
	}

}
