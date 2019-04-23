package shoppee.com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cat_id;

	@Column(name = "cat_name")
	private String cat_name;

	@Column(name = "parent_id")
	private Integer parent_id;

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Integer cat_id, String cat_name, Integer parent_id) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.parent_id = parent_id;
	}

}
