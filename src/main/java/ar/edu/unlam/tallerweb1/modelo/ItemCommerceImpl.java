package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity(name = "commerce_item")
public class ItemCommerceImpl implements ItemCommerce {

    // Esta clase representa a la tabla commerce_item
    // Se tuvo que hacer esto para poder incluir campos extras a la relación como precio y stock
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commerce_id")
    private Commerce commerce;

    public ItemCommerceImpl(){

    }

    // campos extras a la relación item-commerce
    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public Commerce getCommerce(){
        return commerce;
    }

    public void setCommerce(Commerce commerce){
        this.commerce = commerce;
    }

    public Double getPrice(){
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Integer getStock(){
        return stock;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }
}
