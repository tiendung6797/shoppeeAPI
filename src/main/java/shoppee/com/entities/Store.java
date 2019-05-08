package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer store_id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "storeowner_name")
	private String storeowner_name;

	@Column(name = "store_name")
	private String store_name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "bank_name")
	private String bank_name;

	@Column(name = "bank_id")
	private String bank_id;

	public Store() {
		super();
	}

	public Store(Integer store_id, String email, String password, String storeowner_name, String store_name,
			String address, String phone, String bank_name, String bank_id) {
		super();
		this.store_id = store_id;
		this.email = email;
		this.password = password;
		this.storeowner_name = storeowner_name;
		this.store_name = store_name;
		this.address = address;
		this.phone = phone;
		this.bank_name = bank_name;
		this.bank_id = bank_id;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStoreowner_name() {
		return storeowner_name;
	}

	public void setStoreowner_name(String storeowner_name) {
		this.storeowner_name = storeowner_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_id() {
		return bank_id;
	}

	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}

}
