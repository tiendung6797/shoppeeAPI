package shoppee.com.dto;

import shoppee.com.entities.User;

public class ReviewDto {

	private Integer review_id;
	private User user;
	private String star_number;
	private String detail;
	private String active;

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

	public String getStar_number() {
		return star_number;
	}

	public void setStar_number(String star_number) {
		this.star_number = star_number;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewDto(Integer review_id, User user, String star_number, String detail, String active) {
		super();
		this.review_id = review_id;
		this.user = user;
		this.star_number = star_number;
		this.detail = detail;
		this.active = active;
	}

}
