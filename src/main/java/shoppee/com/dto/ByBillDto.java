package shoppee.com.dto;

public class ByBillDto {
	private String bill_number;
	private String user_name;
	private String user_address;
	private double sum_bill;
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

	public double getSum_bill() {
		return sum_bill;
	}

	public void setSum_bill(double sum_bill) {
		this.sum_bill = sum_bill;
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

	public ByBillDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ByBillDto(String bill_number, String user_name, String user_address, double sum_bill, String payment,
			String status) {
		super();
		this.bill_number = bill_number;
		this.user_name = user_name;
		this.user_address = user_address;
		this.sum_bill = sum_bill;
		this.payment = payment;
		this.status = status;
	}

}
