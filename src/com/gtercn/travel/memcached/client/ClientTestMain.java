package com.gtercn.travel.memcached.client;

import java.util.List;

import com.gtercn.travel.bean.Shop;

/**
 * xmemcached客户端 测试
 * 
 * @author ken 2017-2-6 下午01:38:10
 */
public class ClientTestMain {
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		MemcachedClientManager manager = MemcachedClientManager.getInstance();
		for (int i = 0; i < 10; i++) {
			Shop shop = new Shop();
			shop.setShopName(String.valueOf(i));
			manager.storeUpdateList(shop);
		}
		List<Shop> rtList = manager.getKeys(MemcachedClientManager.UpdateObjList);

		long endTime = System.currentTimeMillis();

		// 因为keylist设置了10000个 counter最大值也是10000 所以取出的列表最大为10000个
		System.out.println(rtList.size());
		System.out.println(endTime - beginTime);
	}
}
