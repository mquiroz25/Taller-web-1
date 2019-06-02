package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    @ManyToMany(mappedBy = "items")
    private Set<Commerce> commerces = new HashSet<>();
    @ManyToOne
    private Category category;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<Commerce> getCommerces() {
        return commerces;
    }

    public void setCommerces(Set<Commerce> commerces) {
        this.commerces = commerces;
    }
}
