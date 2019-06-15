package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlImage;
    private String description;
    private String brand;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "item")
    private Set<ItemCommerce> itemCommerces = new HashSet<>();

    public Item() {
    }

    public Item(String urlImage, String description, String brand, Category category) {
        this.urlImage = urlImage;
        this.description = description;
        this.brand = brand;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Commerce> getCommerces(){
        Set<Commerce> result = new HashSet<>();

        for (ItemCommerce itemCommerce:itemCommerces){
            result.add(itemCommerce.getCommerce());
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
