package shoppee.com.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pro_id;

	@Column(name = "store_id")
	private int store_id;

	@Column(name = "cat_id")
	private int cat_id;

	@Column(name = "sale_product")
	private int sale_product;

	@Column(name = "hot_product")
	private int hot_product;

	@Column(name = "active")
	private int active;

	@Column(name = "count_view")
	private int count_view;

	@Column(name = "count_selled")
	private int count_selled;

	@Column(name = "regular_price")
	private double regular_price;

	@Column(name = "sale_price")
	private double sale_price;

	@Column(name = "pro_name")
	private String pro_name;

	@Column(name = "description")
	private String description;

	@Column(name = "color")
	private String color;

	@Column(name = "materials")
	private String materials;

	@Column(name = "made_in")
	private String made_in;

	@Column(name = "date_create")
	private Timestamp date_create;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<File> listFile;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<Size> listSize;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<Review> listReview;

	public Product() {
		super();
	}

	public Product(int pro_id, int store_id, int cat_id, int sale_product, int hot_product, int active, int count_view,
			int count_selled, double regular_price, double sale_price, String pro_name,
			String description, String color, String materials, String made_in, Timestamp date_create) {
		super();
		this.pro_id = pro_id;
		this.store_id = store_id;
		this.cat_id = cat_id;
		this.sale_product = sale_product;
		this.hot_product = hot_product;
		this.active = active;
		this.count_view = count_view;
		this.count_selled = count_selled;
		this.regular_price = regular_price;
		this.sale_price = sale_price;
		this.pro_name = pro_name;
		this.description = description;
		this.color = color;
		this.materials = materials;
		this.made_in = made_in;
		this.date_create = date_create;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getSale_product() {
		return sale_product;
	}

	public void setSale_product(int sale_product) {
		this.sale_product = sale_product;
	}

	public int getHot_product() {
		return hot_product;
	}

	public void setHot_product(int hot_product) {
		this.hot_product = hot_product;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getCount_view() {
		return count_view;
	}

	public void setCount_view(int count_view) {
		this.count_view = count_view;
	}

	public int getCount_selled() {
		return count_selled;
	}

	public void setCount_selled(int count_selled) {
		this.count_selled = count_selled;
	}

	public double getRegular_price() {
		return regular_price;
	}

	public void setRegular_price(double regular_price) {
		this.regular_price = regular_price;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getMade_in() {
		return made_in;
	}

	public void setMade_in(String made_in) {
		this.made_in = made_in;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public Set<File> getListFile() {
		return listFile;
	}

	public void setListFile(Set<File> listFile) {
		this.listFile = listFile;
	}

	public Set<Size> getListSize() {
		return listSize;
	}

	public void setListSize(Set<Size> listSize) {
		this.listSize = listSize;
	}

}
