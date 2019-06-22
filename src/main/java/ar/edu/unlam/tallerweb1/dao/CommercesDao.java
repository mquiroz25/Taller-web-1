package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

import java.util.List;

public interface CommercesDao {
    List<Commerce> getCommerces();
    Commerce getCommerceById(Long id);
}
