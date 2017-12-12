package edu.karazin.shop.model;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity(name = "Product")
@Table(name = "product")
@SQLDelete(sql =
    "UPDATE product " +
    "SET deleted = true " +
    "WHERE id = ?")
//@Loader(namedQuery = "findAllByDeleted")
//@NamedQuery(name = "findAllByDeleted", query =
//    "SELECT p " +
//    "FROM Product p " +
//    "WHERE " +
//    "p.deleted = ?1")
//@Where(clause = "deleted = false")
public class Product extends BaseEntity {

	@Id
    @GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	
	@Lob
	private byte[] image;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.REMOVE)
//	@SQLDelete(sql="DELETE FROM ORDER_ITEM WHERE product_id = ?")
    private List<OrderItem> orderItems;
	
	private String imageMimeType;
	private double cost;
	private int balance;

	public Product() {
	}

	public Product(String title, String description) {
		this(title, description, null, null, 0.0d, 0);
	}

	public Product(String title, String description, byte[] image, String imageMimeType, double cost, int balance) {
		this.title = title;
		this.description = description;
		this.image = image;
		this.imageMimeType = imageMimeType;
		this.cost = cost;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getImage() {
        return image;
	}
	
	public void setImage(byte[] image) {
		if (image != null && image.length > 0) {
			this.image = image;
		}
	}
	
	public String getImageString() {
		if (image != null) {
			byte[] encodeBase64 = Base64.encodeBase64(image);
			try {
				return new String(encodeBase64, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public String getImageMimeType() {
		return imageMimeType;
	}

	public void setImageMimeType(String imageMimeType) {
		if (imageMimeType != null && !imageMimeType.isEmpty()) {
			this.imageMimeType = imageMimeType;
		}
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
