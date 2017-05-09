package com.gtercn.travel.redis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service(value="redisTemplateCacheUtil")
public class RedisTemplateCacheUtil<T> {
	@Autowired
	private  RedisTemplate<String, T> redisTemplate;

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * 
	 * @param key
	 *            缓存的键值
	 * @param value
	 *            缓存的值
	 * @return 缓存的对象
	 */
	public ValueOperations<String, T> setCacheObject(String key, T value) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		operation.set(key, value);
		return operation;
	}

	/**
	 * 获得缓存的基本对象。
	 * 
	 * @param key
	 *            缓存键值
	 * @param operation
	 * @return 缓存键值对应的数据
	 */
	public T getCacheObject(String key/* ,ValueOperations<String,T> operation */) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 缓存List数据
	 * 
	 * @param key
	 *            缓存的键值
	 * @param dataList
	 *            待缓存的List数据
	 * @return 缓存的对象
	 */
	public ListOperations<String, T> setCacheList(String key,
			List<T> dataList) {
		ListOperations<String,T> listOperation = redisTemplate.opsForList();
		boolean result =listOperation.getOperations().hasKey(key);
		if (null != dataList && !result) {
			int size = dataList.size();
			for (int i = 0; i < size; i++) {
				listOperation.rightPush(key, dataList.get(i));
			}
		}
		return listOperation;
	}

	/**
	 * 获得缓存的list对象
	 * 
	 * @param key
	 *            缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public List<T> getCacheList(String key) {
		/* 
		 * Long size = listOperation.size(key);
		 * List<T> dataList = new ArrayList<T>(Integer.valueOf(String.valueOf(size)));
		 * for (int i = 0; i < size; i++) {
			//左边出栈,这不是查询,这是取出
			T t=(T) listOperation.leftPop(key);
			dataList.add(t);
		}*/
		ListOperations<String, T> listOperation = redisTemplate.opsForList();
		//查询list从0开始,到index位的值,如果是-1,则表示查询出来所有
		List<T> result =listOperation.range(key, 0, -1);
		return result;
	}

	/**
	 * 缓存Set
	 * 
	 * @param key
	 *            缓存键值
	 * @param dataSet
	 *            缓存的数据
	 * @return 缓存数据的对象
	 */
	@SuppressWarnings("unchecked")
	public BoundSetOperations<String, T> setCacheSet(String key,
			Set<T> dataSet) {
		BoundSetOperations<String,T> setOperation = redisTemplate
				.boundSetOps(key);
		/*
		 * T[] t = (T[]) dataSet.toArray(); setOperation.add(t);
		 */
		Iterator<T> it = dataSet.iterator();
		while (it.hasNext()) {
			T t=it.next();
			setOperation.add(t);
		}
		return setOperation;
	}

	/**
	 * 获得缓存的set
	 * 
	 * @param key
	 * @param operation
	 * @return
	 */
	public Set<T> getCacheSet(String key/*,BoundSetOperations<String,T> operation */) {
		Set<T> dataSet = new HashSet<T>();
		BoundSetOperations<String, T> operation = redisTemplate
				.boundSetOps(key);
		Long size = operation.size();
		for (int i = 0; i < size; i++) {
			dataSet.add(operation.pop());
		}
		return dataSet;
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 * @return
	 */
	public HashOperations<String, String, T> setCacheMap(String key,
			Map<String, T> dataMap) {
		HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<String, T> entry : dataMap.entrySet()) {
				/*
				 * System.out.println("Key = " + entry.getKey() + ", Value = " +
				 * entry.getValue());
				 */
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @param hashOperation
	 * @return
	 */
	public Map<Object, Object> getCacheMap(String key/*
													 * ,HashOperations<String,String
													 * ,T> hashOperation
													 */) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
		/* Map<String, T> map = hashOperation.entries(key); */
		return map;
	}

	/**
	 * 缓存Map
	 * 
	 * @param key
	 * @param dataMap
	 * @return
	 */
	public HashOperations<String, Integer, T> setCacheIntegerMap(
			String key, Map<Integer, T> dataMap) {
		HashOperations<String, Integer, T> hashOperations = redisTemplate.opsForHash();
		if (null != dataMap) {
			for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
				hashOperations.put(key, entry.getKey(), entry.getValue());
			}
		}
		return hashOperations;
	}

	/**
	 * 获得缓存的Map
	 * 
	 * @param key
	 * @param hashOperation
	 * @return
	 */
	public Map<Object,Object> getCacheIntegerMap(String key) {
		Map<Object,Object> map = redisTemplate.opsForHash().entries(key);
		return map;
	}

}
