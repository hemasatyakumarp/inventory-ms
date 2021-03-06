package com.hackerrank.inventory.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventory{
	
	/**
	 * 
	 */
	
	@Id
	private Long id;
	private String productName;
	private String productLable;
	private int inventoryOnHand;
	private int minQtyReq;
	private double price;
	
	public Inventory(Long id, String productName, String productLable, int inventoryOnHand, int minQtyReq,
			double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.productLable = productLable;
		this.inventoryOnHand = inventoryOnHand;
		this.minQtyReq = minQtyReq;
		this.price = price;
	}
	
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}


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
