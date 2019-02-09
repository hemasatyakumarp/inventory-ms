package com.hackerrank.sku.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sku implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String productName;
	private String productLable;
	private int inventoryOnHand;
	private int minQtyReq;
	private double price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long skuId) {
		id = skuId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLable() {
		return productLable;
	}
	public void setProductLable(String productLable) {
		this.productLable = productLable;
	}
	public int getInventoryOnHand() {
		return inventoryOnHand;
	}
	public void setInventoryOnHand(int inventoryOnHand) {
		this.inventoryOnHand = inventoryOnHand;
	}
	public int getMinQtyReq() {
		return minQtyReq;
	}
	public void setMinQtyReq(int minQtyReq) {
		this.minQtyReq = minQtyReq;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
