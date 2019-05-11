package shoppee.com.payload;

public class AdminRequest {

	private String user_name;
	private String password;
	private String fullname;
	private int role_id;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public AdminRequest(String user_name, String password, String fullname, int role_id) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.fullname = fullname;
		this.role_id = role_id;
	}

}
