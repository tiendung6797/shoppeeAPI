package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer review_id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "pro_id", nullable = false)
	private Product product;

	@Column(name = "star_number")
	private Integer star_number;

	@Column(name = "detail")
	private String detail;

	@Column(name = "active")
	private Integer active;

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getStar_number() {
		return star_number;
	}

	public void setStar_number(Integer star_number) {
		this.star_number = star_number;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getActive() {
		return active;
	}

	public void Integer(Integer active) {
		this.active = active;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Integer review_id, User user, Product product, Integer star_number, String detail, Integer active) {
		super();
		this.review_id = review_id;
		this.user = user;
		this.product = product;
		this.star_number = star_number;
		this.detail = detail;
		this.active = active;
	}

}
