package com.gtercn.travel.memcached;

import java.util.Date;

/**
 * 分布式缓存操作接口
 * 系统操作缓存的唯一接口
 *
 */
public interface MemCachedService {
	
	/**
	 * 保存数据
	 * @param key	保存的键值
	 * @param object	保存的对象
	 * @return	true-保存成功,
	 * false-保存失败
	 */
	boolean save(String key, Object object);
	
	/**
	 * 保存数据
	 * 可以设置数据的过期时间
	 * @param key	保存的键值
	 * @param object	保存的对象
	 * @param deadline	过期时间
	 * @return	true-保存成功,
	 * false-保存失败
	 */
	boolean save(String key, Object object, Date deadline);
	
	/**
	 * 保存数据
	 * 可以设置数据在过多少毫秒时过期
	 * @param key	保存的键值
	 * @param object	保存的对象
	 * @param deadline	过期时间
	 * @return	true-保存成功,
	 * false-保存失败
	 */
	boolean save(String key, Object object, long deadline);
	
	/**
	 * 根据键值删除缓存数据
	 * 如果删除缓存中没有保存的key值则返回false
	 * @param key	保存的键值
	 * @return	true-删除成功,
	 * false-删除失败
	 */
	boolean delete(String key);
	
	/**
	 * 根据键值获取缓存中的数据
	 * @param key	保存的键值
	 * @return	保存的对象,
	 * 如果没有该对象则返回null
	 */
	Object getDataByCache(String key);
	
	/**
	 * 根据键值集合
	 * 获取缓存中的数据集合
	 * @param keys	保存的键值集合
	 * @return	保存的对象集合,
	 * 如果没有该对象则返回null
	 */
	Object[] getDataByCache(String[] keys);

}