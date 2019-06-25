package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

import java.util.List;

public interface CommerceService {
    List<Commerce> getCommercesByDistance(Long distance, Double userLat, Double userLon);
    Commerce getCommerceById(Long id);
}
