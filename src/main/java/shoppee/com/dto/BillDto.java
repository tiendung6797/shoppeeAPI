package shoppee.com.dto;

public class BillDto {

	private String store_name;
	private String store_address;
	private String store_phone;
	private double sum_cost;
	private String bank_name;
	private String bank_id;

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	public String getStore_phone() {
		return store_phone;
	}

	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}

	public double getSum_cost() {
		return sum_cost;
	}

	public void setSum_cost(double sum_cost) {
		this.sum_cost = sum_cost;
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

	public BillDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillDto(String store_name, String store_address, String store_phone, double sum_cost, String bank_name,
			String bank_id) {
		super();
		this.store_name = store_name;
		this.store_address = store_address;
		this.store_phone = store_phone;
		this.sum_cost = sum_cost;
		this.bank_name = bank_name;
		this.bank_id = bank_id;
	}

}
