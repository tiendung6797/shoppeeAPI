package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;
	private String role_name;
//	@OneToMany(mappedBy="roleId", cascade = CascadeType.ALL,targetEntity=User.class)
//	private Set users;
	
	public Role(String role_name) {
		super();
		this.role_name = role_name;
	}
	
	public Role() {
		super();
	}
	public Role(int role_id, String role_name) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
//	public Set getUsers() {
//		return users;
//	}
//	public void setUsers(Set users) {
//		this.users = users;
//	}
	
	
}
