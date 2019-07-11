package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;

public interface ItemCommerceService {
    boolean notifyNoStock(Long idCommerce, Long idItem);
    ItemCommerce getItemCommerceById(Long idCommerce, Long idItem);
    Integer checkStock(Long idCommerce, Long idItem);
    void deductStock(Long idCommerce, Long idItem, Integer amount);
}
