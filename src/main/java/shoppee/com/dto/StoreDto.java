package shoppee.com.dto;

public class StoreDto {

	private Integer store_id;
	private String email;
	private String storeowner_name;
	private String store_name;
	private String address;
	private String phone;
	private String bank_name;
	private String bank_id;

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

	public StoreDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreDto(Integer store_id, String email, String storeowner_name, String store_name, String address,
			String phone, String bank_name, String bank_id) {
		super();
		this.store_id = store_id;
		this.email = email;
		this.storeowner_name = storeowner_name;
		this.store_name = store_name;
		this.address = address;
		this.phone = phone;
		this.bank_name = bank_name;
		this.bank_id = bank_id;
	}

}
