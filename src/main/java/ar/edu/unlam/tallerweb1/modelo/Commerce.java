package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Commerce {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Set<ItemCommerce> itemCommerces = new HashSet<>();

    public Commerce(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "commerce")
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
}
