package com.gtercn.travel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gtercn.travel.bean.Shop;
import com.gtercn.travel.dao.ShopDao;
import com.gtercn.travel.service.ShopService;
import com.gtercn.travel.utils.CachedKeys;

@Service(value = "shopService")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao dao;
	@Autowired
	protected RedisTemplate<String, List<Shop>> redisTemplate;

	public List<Shop> shopList(Map<String, Object> map) {
		return dao.shopList(map);
	}

	public Integer getTotalCount() {
		return dao.getTotalCount();
	}

	@Override
	public Shop queryByPrimaryKey(String id) {
		return dao.queryByPrimaryKey(id);
	}

	@Override
	public void redisTemplateSave(final Shop shop) {
		//TODO Auto-generated method stub
		/*
		 * boolean result=redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				//指定serializer策略	
				RedisSerializer<String> redis = redisTemplate
						.getStringSerializer();
				byte[] key = redis.serialize("shop.id." + shop.getId());
				byte[] value = redis.serialize(shop.getShopName());
				//SET if Not eXists
				return connection.setNX(key, value);
			}
		});
		return result;*/
		
		
		//通过配置文件指定serializer策略
//		ValueOperations<String, Shop> valueOPS=redisTemplate.opsForValue();
//		valueOPS.set(shop.getId(),shop);
	}

	@Override
	public void redisTemplateSaveList(List<Shop> list) {
		Assert.notEmpty(list);
		ListOperations<String, List<Shop>> valueOPS=redisTemplate.opsForList();
		valueOPS.leftPush(CachedKeys.SHOP_KEYS_LIST,list);
	}
	
	@Override
	public List<Shop> redisTemplateGetList(String keys) {
		ListOperations<String, List<Shop>> valueOPS=redisTemplate.opsForList();
		//弹出元素，就删除了
		List<Shop> result=valueOPS.leftPop(keys);
		return result;
	}

	@Override
	public Shop redisTemplateGetShop(final String shopId) {
		return null;
	}

	@Override
	public void redisTemplateDelete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public void redisTemplateUpdate(final Shop shop) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> redis = redisTemplate
						.getStringSerializer();
				byte[] key = redis.serialize(shop.getId());
				byte[] name = redis.serialize(shop.getShopName());
				connection.set(key, name);
				return null;
			}
		});
	}


}
