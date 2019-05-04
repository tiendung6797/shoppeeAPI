package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productofbill")
public class ProductBill implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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
	
	/*@OneToOne
	@JoinColumn(name = "user_id")
	private User user;*/

	@Column(name = "store_id")
	private Integer store_id;
	
	/*@OneToOne
	@JoinColumn(name = "store_id")
	private Store store;*/

	@Column(name = "payment_id")
	private Integer payment_id;
	
	/*@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
*/
	@Column(name = "cost")
	private Float cost;

	@Column(name = "status")
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public Integer getquantity() {
		return quantity;
	}

	public void setquantity(Integer quantity) {
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

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}*/

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ProductBill() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public ProductBill(Integer id, String pro_name, Integer quantity, String color, String size, String bill_number,
			User user, Store store, Payment payment, Float cost, Integer status) {
		super();
		this.id = id;
		this.pro_name = pro_name;
		this.quantity = quantity;
		this.color = color;
		this.size = size;
		this.bill_number = bill_number;
		this.user = user;
		this.store = store;
		this.payment = payment;
		this.cost = cost;
		this.status = status;
	}
*/
	public ProductBill(Integer id, String pro_name, Integer quantity, String color, String size, String bill_number,
			Integer user_id, Integer store_id, Integer payment_id, Float cost, Integer status) {
		super();
		this.id = id;
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
