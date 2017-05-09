package com.gtercn.travel.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.gtercn.travel.bean.Shop;
import com.gtercn.travel.memcached.XMemcachedService;
import com.gtercn.travel.redis.RedisTemplateCacheUtil;
import com.gtercn.travel.service.ShopService;
import com.gtercn.travel.utils.CachedKeys;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ShopAction {
	@Autowired
	private ShopService shopService;
	@Autowired
	private XMemcachedService xMemcachedService;
	@Autowired
    protected RedisTemplate<Serializable, Serializable> redisTemplate;
	@Autowired
	private RedisTemplateCacheUtil<Shop> redisTemplateCacheUtil;
	/**
	 * 店铺列表
	 * @return
	 */
	public String shop_list(){
		Map<String,Object> map =new HashMap<String,Object>();
		ActionContext context = ActionContext.getContext();
		try {
			List<Shop> list = shopService.shopList(map);
			if(!xMemcachedService.isKeyExist(CachedKeys.SHOP_KEYS_LIST))
				xMemcachedService.setStoreList(list);
			context.put("shopList",list);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "shop_list";
	}
	
	/**
	 * 从Memcached缓存中获取店铺
	 * @return
	 * 2017-2-6 上午10:12:15
	 */
	public String cache_list(){
		ActionContext context=ActionContext.getContext();
		try {
			Collection<String> list=new ArrayList<String>();
			list.add(CachedKeys.SHOP_KEYS_LIST);
			Map<String,List<Shop>> result= xMemcachedService.getStoreList(list);
			List<Shop> shopList =result.get(CachedKeys.SHOP_KEYS_LIST);
			context.put("shopList", shopList);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "cache_list";
	}
	
	/**
	 * Redis
	 * 
	 * 加入缓存
	 * @return
	 * 2017-4-18 下午02:15:15
	 */
	public String redisTemplateAddList(){
		Map<String,Object> map =new HashMap<String,Object>();
		ActionContext context = ActionContext.getContext();
		try {
			List<Shop> list = shopService.shopList(map);
//			shopService.redisTemplateSaveList(list);
			redisTemplateCacheUtil.setCacheList(CachedKeys.SHOP_KEYS_LIST, list);
			context.put("shopList",list);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "shop_list";
	}
	
	/**
	 * 缓存的数据
	 * @return
	 * 2017-4-18 下午02:13:55
	 */
	public String redisTemplateGetList(){
		ActionContext context = ActionContext.getContext();
		try {
//			List<Shop> result=shopService.redisTemplateGetList(CachedKeys.SHOP_KEYS_LIST);
			List<Shop> result = redisTemplateCacheUtil.getCacheList(CachedKeys.SHOP_KEYS_LIST);
			context.put("shopList",result);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "cache_list";
	}
	
}
