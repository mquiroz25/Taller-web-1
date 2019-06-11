package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Commerce;

import java.util.List;

public interface CommerceService {
    List<Commerce> getCommercesByDistance(Long distance, Double userLat, Double userLon);
}
