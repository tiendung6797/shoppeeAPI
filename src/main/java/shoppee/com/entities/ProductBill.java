package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productofbill")
public class ProductBill implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "pro_id")
	private Integer pro_id;

	@Column(name = "pro_name")
	private String pro_name;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "color")
	private String color;

	@Column(name = "size")
	private String size;

	@Column(name = "bill_number")
	private String bill_number;

	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "store_id")
	private Integer store_id;

	@Column(name = "payment_id")
	private Integer payment_id;

	@Column(name = "cost")
	private double cost;

	@Column(name = "status")
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPro_id() {
		return pro_id;
	}

	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
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

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
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

	public ProductBill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductBill(Integer id, Integer pro_id, String pro_name, Integer quantity, String color, String size,
			String bill_number, Integer user_id, Integer store_id, Integer payment_id, double cost, String status) {
		super();
		this.id = id;
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.quantity = quantity;
		this.color = color;
		this.size = size;
		this.bill_number = bill_number;
		this.user_id = user_id;
		this.store_id = store_id;
		this.payment_id = payment_id;
		this.cost = cost;
		this.status = status;
	}

}
