package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemCommerceDao;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;

public interface ItemCommerceService {
    boolean notifyNoStock(Long idCommerce, Long idItem);
}
