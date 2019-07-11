package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "reserve")
public class Reserve {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
    @ManyToOne
    private Item item;
    @ManyToOne
    private Commerce commerce;
    private Double price;
    private Integer amount;
	@Transient
    private Long commerceId;
	@Transient
    private Long itemId;
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Commerce getCommerce() {
		return commerce;
	}

	public void setCommerce(Commerce commerce) {
		this.commerce = commerce;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(Long commerceId) {
		this.commerceId = commerceId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
}
