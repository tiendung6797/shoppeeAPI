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
	
	public Role(String roleName) {
		super();
		this.role_name = roleName;
	}
	public Role() {
		super();
	}
	
	public Role(int roleId, String roleName) {
		super();
		this.role_id = roleId;
		this.role_name = roleName;
	}
	public int getroleId() {
		return role_id;
	}
	public void setroleId(int roleId) {
		this.role_id = roleId;
	}
	public String getroleName() {
		return role_name;
	}
	public void setroleName(String roleName) {
		this.role_name = roleName;
	}
	
//	public Set getUsers() {
//		return users;
//	}
//	public void setUsers(Set users) {
//		this.users = users;
//	}
	
	
}
