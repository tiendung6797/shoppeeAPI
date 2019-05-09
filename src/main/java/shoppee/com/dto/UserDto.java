package shoppee.com.dto;

public class UserDto {

	private Integer user_id;
	private String email;
	private String fullname;
	private String avatar;
	private String phone;
	private String address;
	private String date_create;
	private Integer enable;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate_create() {
		return date_create;
	}

	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Integer user_id, String email, String fullname, String avatar, String phone, String address,
			String date_create, Integer enable) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.date_create = date_create;
		this.enable = enable;
	}

}
