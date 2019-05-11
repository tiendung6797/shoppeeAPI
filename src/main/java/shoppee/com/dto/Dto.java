package shoppee.com.dto;

public class Dto {

	private Integer store_id;
	private double sum_cost;

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public double getSum_cost() {
		return sum_cost;
	}

	public void setSum_cost(double sum_cost) {
		this.sum_cost = sum_cost;
	}

	public Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dto(Integer store_id, double sum_cost) {
		super();
		this.store_id = store_id;
		this.sum_cost = sum_cost;
	}

}
