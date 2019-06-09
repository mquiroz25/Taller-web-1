package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity(name = "commerce_item")
public class ItemCommerce {

    // Esta clase representa a la tabla commerce_item
    // Se tuvo que hacer esto para poder incluir campos extras a la relación como precio y stock
    private Long id;
    private Item item;
    private Commerce commerce;

    public ItemCommerce(){

    }

    // campos extras a la relación item-commerce
    private Double price;
    private Integer stock;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commerce_id")
    public Commerce getCommerce(){
        return commerce;
    }

    public void setCommerce(Commerce commerce){
        this.commerce = commerce;
    }

    @Column(name = "price")
    public Double getPrice(){
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    @Column(name = "stock")
    public Integer getStock(){
        return stock;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }
}
