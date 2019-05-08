package shoppee.com.dto;

import shoppee.com.entities.Product;
import shoppee.com.entities.Store;

public class ProductBillDto {

	private Integer id;
	private Product product;
	private Integer quantity;
	private String color;
	private String size;
	private String bill_number;
	private Integer user_id;
	private Store store;
	private Integer payment_id;
	private double cost;
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getBill_number() {
		return bill_number;
	}

	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProductBillDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductBillDto(Integer id, Product product, Integer quantity, String color, String size, String bill_number,
			Integer user_id, Store store, Integer payment_id, double cost, String status) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.color = color;
		this.size = size;
		this.bill_number = bill_number;
		this.user_id = user_id;
		this.store = store;
		this.payment_id = payment_id;
		this.cost = cost;
		this.status = status;
	}

}
