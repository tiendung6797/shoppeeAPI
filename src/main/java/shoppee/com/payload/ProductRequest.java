package shoppee.com.payload;

public class ProductRequest {
	private int cat_id;
	private int sale_product;
	private int hot_product;
	private int active;
	private int size1;
	private int size2;
	private int size3;
	private int size4;
	private int size5;
	private double regular_price;
	private double sale_price;
	private String pro_name;
	private String size_type;
	private String description;
	private String color;
	private String materials;
	private String made_in;
	public ProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductRequest(int cat_id, int sale_product, int hot_product, int active,
			int size1, int size2, int size3, int size4, int size5, double regular_price, double sale_price,
			String pro_name, String size_type, String description, String color, String materials, String made_in) {
		super();
		this.cat_id = cat_id;
		this.sale_product = sale_product;
		this.hot_product = hot_product;
		this.active = active;
		this.size1 = size1;
		this.size2 = size2;
		this.size3 = size3;
		this.size4 = size4;
		this.size5 = size5;
		this.regular_price = regular_price;
		this.sale_price = sale_price;
		this.pro_name = pro_name;
		this.size_type = size_type;
		this.description = description;
		this.color = color;
		this.materials = materials;
		this.made_in = made_in;
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
	public int getSize1() {
		return size1;
	}
	public void setSize1(int size1) {
		this.size1 = size1;
	}
	public int getSize2() {
		return size2;
	}
	public void setSize2(int size2) {
		this.size2 = size2;
	}
	public int getSize3() {
		return size3;
	}
	public void setSize3(int size3) {
		this.size3 = size3;
	}
	public int getSize4() {
		return size4;
	}
	public void setSize4(int size4) {
		this.size4 = size4;
	}
	public int getSize5() {
		return size5;
	}
	public void setSize5(int size5) {
		this.size5 = size5;
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
	public String getSize_type() {
		return size_type;
	}
	public void setSize_type(String size_type) {
		this.size_type = size_type;
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
	
}
