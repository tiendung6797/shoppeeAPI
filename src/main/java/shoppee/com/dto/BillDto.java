package shoppee.com.dto;

public class BillDto {

	private String bill_number;
	private String user_name;
	private String user_address;
	private double sum_cost;
	private String payment;
	private String status;

	public String getBill_number() {
		return bill_number;
	}

	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public double getSum_cost() {
		return sum_cost;
	}

	public void setSum_cost(double sum_cost) {
		this.sum_cost = sum_cost;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BillDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillDto(String bill_number, String user_name, String user_address, double sum_cost, String payment,
			String status) {
		super();
		this.bill_number = bill_number;
		this.user_name = user_name;
		this.user_address = user_address;
		this.sum_cost = sum_cost;
		this.payment = payment;
		this.status = status;
	}

}
