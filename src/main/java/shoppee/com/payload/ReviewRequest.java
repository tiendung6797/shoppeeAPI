package shoppee.com.payload;

import javax.validation.constraints.NotBlank;

public class ReviewRequest {

	@NotBlank
	private Integer user_id;

	@NotBlank
	private Integer pro_id;

	@NotBlank
	private Integer star_number;

	@NotBlank
	private String detail;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getPro_id() {
		return pro_id;
	}

	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
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

}
