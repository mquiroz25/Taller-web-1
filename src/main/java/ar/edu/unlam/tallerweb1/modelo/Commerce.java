package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Commerce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commerce_id;
    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "commerce")
    private Set<ItemCommerce> itemCommerces = new HashSet<>();

    public Commerce() {
    }

    public Commerce(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getCommerce_id() {
        return commerce_id;
    }

    public void setCommerce_id(Long commerce_id) {
        this.commerce_id = commerce_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ItemCommerce> getItemCommerces(){
        return itemCommerces;
    }

    public void setItemCommerces(Set<ItemCommerce> commerces){
        this.itemCommerces = commerces;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<Item> getItems(){
        Set<Item> result = new HashSet<>();

        for (ItemCommerce itemCommerce:itemCommerces){
            result.add(itemCommerce.getItem());
        }

        return result;
    }
}
