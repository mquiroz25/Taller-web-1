package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    private Long id;
    private String brand;
    private Category category;
    private Set<ItemCommerce> itemCommerces = new HashSet<ItemCommerce>();

    public Item(){

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    @OneToMany(mappedBy = "item")
    public Set<ItemCommerce> getItemCommerces() {
        return itemCommerces;
    }

    public void setItemCommerces(Set<ItemCommerce> itemCommerces) {
        this.itemCommerces = itemCommerces;
    }

    public void addItemCommerce(ItemCommerce itemCommerce){
        this.itemCommerces = itemCommerces;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
