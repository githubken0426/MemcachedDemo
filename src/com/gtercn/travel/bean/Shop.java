package com.gtercn.travel.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺
 * @author Administrator
 * 2016-5-30 下午04:00:42
 *
 */
public class Shop implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String shopName;
	private String shopDescription;
	private String province;
	private String city;
	private String district;
	private String detailAddress;
	private String telNumberList;
	private Date insertTime;
	
	public Shop(){}
	public Shop(String id,String name,String description){
		this.id=id;
		this.shopName=name;
		this.shopDescription=description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDescription() {
		return shopDescription;
	}
	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getTelNumberList() {
		return telNumberList;
	}
	public void setTelNumberList(String telNumberList) {
		this.telNumberList = telNumberList;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}
