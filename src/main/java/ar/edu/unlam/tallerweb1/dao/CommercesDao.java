package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import java.util.List;

public interface CommercesDao {
    List<Commerce> getCommerces();
    Commerce getCommerceById(Long id);
}