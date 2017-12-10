package edu.karazin.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.karazin.shop.web.ProductEditController;

@Entity
public class OrderItem {

	private static final Logger log = LoggerFactory.getLogger(ProductEditController.class);

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private double price;

	public OrderItem() {
	}

	public OrderItem(Product product, int amount, double price) {
		this(product, amount, price, null);
	}

	public OrderItem(Product product, int amount, double price, Order order) {
		this.order = order;
		this.product = product;
		this.amount = amount;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		Product prod = this.getProduct();
		int balance = prod.getBalance();
		if (balance > amount) {
			this.amount = amount;
//			prod.setBalance(balance - amount);
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
