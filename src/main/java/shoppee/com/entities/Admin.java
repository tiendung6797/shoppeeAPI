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
@Table(name = "admin")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer admin_id;
	private String username;
	private String password;

	private String fullname;

	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role_id;

	public Admin() {
		super();
	}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Admin(String username, String password, Role role_id) {
		super();
		this.username = username;
		this.password = password;
		this.role_id = role_id;
	}

	public Admin(Integer admin_id, String username, String password, String fullname,Role role_id) {
		super();
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role_id = role_id;
	}

	public Integer getId() {
		return admin_id;
	}

	public void setId(Integer admin_id) {
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

	public Role getRole() {
		return role_id;
	}

	public void setRole(Role role) {
		this.role_id = role;
	}

	
}