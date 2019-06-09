package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
