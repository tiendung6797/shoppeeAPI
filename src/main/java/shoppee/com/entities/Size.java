package shoppee.com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer size_id;

	@Column(name = "size_name")
	private String size_name;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "pro_id", nullable = false)
	private Product product;

	public Size() {
		super();
	}

	public Size(Integer size_id, String size_name, int quantity, Product product) {
		super();
		this.size_id = size_id;
		this.size_name = size_name;
		this.quantity = quantity;
		this.product = product;
	}

	public Integer getSize_id() {
		return size_id;
	}

	public void setSize_id(Integer size_id) {
		this.size_id = size_id;
	}

	public String getSize_name() {
		return size_name;
	}

	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*public Product getProduct() {
		return product;
	}*/

	public void setProduct(Product product) {
		this.product = product;
	}


}
