package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;

public interface ItemCommerceDao {
    void notifyNoStock(ItemCommerce itemCommerce);
    ItemCommerce getItemCommerceById(Long idCommerce, Long idItem);
    Integer getStock(Long idCommerce, Long idItem);
    void deductStock(Long idCommerce, Long idItem, Integer amount);
}
