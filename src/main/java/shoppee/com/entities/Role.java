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
	private int roleId;
	private String roleName;
//	@OneToMany(mappedBy="roleId", cascade = CascadeType.ALL,targetEntity=User.class)
//	private Set users;
	
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
	public Role() {
		super();
	}
	
	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public int getroleId() {
		return roleId;
	}
	public void setroleId(int roleId) {
		this.roleId = roleId;
	}
	public String getroleName() {
		return roleName;
	}
	public void setroleName(String roleName) {
		this.roleName = roleName;
	}
//	public Set getUsers() {
//		return users;
//	}
//	public void setUsers(Set users) {
//		this.users = users;
//	}
	
	
}
