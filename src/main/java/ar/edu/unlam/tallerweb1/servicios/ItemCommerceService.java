package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;

public interface ItemCommerceService {
    boolean notifyNoStock(Long idCommerce, Long idItem);
    ItemCommerce getItemCommerceById(Long idCommerce, Long idItem);
    Integer checkAvailability(Long idCommerce, Long idItem) throws Exception;
    void deductStock(Long idCommerce, Long idItem, Integer amount) throws Exception;
}
