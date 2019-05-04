package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payment_id;

	@Column(name = "payment_name")
	private String payment_name;

	/*@OneToOne(mappedBy = "payment", fetch = FetchType.EAGER)
	private ProductBill productBill;*/

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_name() {
		return payment_name;
	}

	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Integer payment_id, String payment_name) {
		super();
		this.payment_id = payment_id;
		this.payment_name = payment_name;
	}

}
