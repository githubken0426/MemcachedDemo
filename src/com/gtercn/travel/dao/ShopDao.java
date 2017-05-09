package com.gtercn.travel.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.travel.bean.Shop;

@Repository
public interface ShopDao {
	
	/**
	 * 店铺列表
	 * @param user
	 * @return
	 */
	public List<Shop> shopList(Map<String,Object> map);
	
	public Integer getTotalCount();
	
	Shop queryByPrimaryKey(String id);
}
