package shoppee.com.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String file_id;

	private String file_name;

	private String file_type;

	@Lob
	private byte[] data;
	
	@ManyToOne
	@JoinColumn(name = "pro_id", nullable = false)
	private Product product;

	public File() {

	}
	

	public File(String file_name, String file_type, byte[] data, Product product) {
		super();
		this.file_name = file_name;
		this.file_type = file_type;
		this.data = data;
		this.product = product;
	}
	

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	/*public Product getProduct() {
		return product;
	}*/

	public void setProduct(Product product) {
		this.product = product;
	}

}
