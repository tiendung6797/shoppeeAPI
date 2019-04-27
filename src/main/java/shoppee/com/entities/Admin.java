package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer admin_id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "date_create")
	private String date_create;

	@Column(name = "role")
	private String role;

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getDate_create() {
		return date_create;
	}

	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer admin_id, String username, String password, String fullname, String date_create, String role) {
		super();
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.date_create = date_create;
		this.role = role;
	}

}