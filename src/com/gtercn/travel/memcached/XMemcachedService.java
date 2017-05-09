package com.gtercn.travel.memcached;

import java.util.Collection;
import java.util.Map;

import net.rubyeye.xmemcached.GetsResponse;

public interface XMemcachedService {
	/**
	 * 保存缓存对象
	 * @param key
	 * @param exp
	 * @param value
	 * @return
	 * 2017-2-14 上午08:54:48
	 */
	public boolean add(final String key, final int exp, final Object value);
	public void addWithNoReply(final String key, final int exp,
			final Object value);
	
	public boolean set(final String key, final int exp,
			final Object value) ;
	public void setWithNoReply(final String key, final int exp,
			final Object value) ;
	
	public <T> T get(final String key, final long timeout);
//	public <T> GetsResponse<T> gets(final String key, final long timeout);
	/**
	 * 删除缓存对象
	 * @param key
	 * 2017-2-8 下午01:33:04
	 */
	public void delete(String key);
	public void deleteWithNoReply(final String key);
	/**
	 * 替换缓存对象
	 * @param key
	 * @param exp
	 * @param value
	 * @return
	 * 2017-2-14 上午08:55:18
	 */
	public boolean replace(final String key, final int exp, final Object value);
	public void replaceWithNoReply(final String key, final int exp,
			final Object value);
	
	public <T> Map<String, GetsResponse<T>> gets(Collection<String> coll);
	
	public boolean cas(final String key, final int exp, final Object value,
			final long cas);
	/**
	 * 计数器取值，支持分布式系统
	 * @param min
	 * @param max
	 * @param key
	 * @return
	 * 2017-2-8 下午01:35:43
	 */
	public long getCount(long min, long max, String key);
	
	/**
	 * 存入list_index-value
	 * @param <T>
	 * @param t
	 * 2017-2-8 下午01:32:21
	 */
	public <T> void setStoreList(Collection<T> list);
	public <T> Map<String,T> getStoreList(Collection<String> keys);
	
	public void authority();

	public <T> boolean isKeyExist(final String key);
	
}
