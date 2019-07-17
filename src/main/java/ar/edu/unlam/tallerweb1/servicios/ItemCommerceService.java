package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import org.springframework.ui.ModelMap;

public interface ItemCommerceService {
    boolean notifyNoStock(Long idCommerce, Long idItem);
    ItemCommerce getItemCommerceById(Long idCommerce, Long idItem);
    Integer checkStock(Long idCommerce, Long idItem);
    void deductStock(Long idCommerce, Long idItem, Integer amount);
    ModelMap getModelForView(
            Long idItem,
            String category,
            Double latitude,
            Double longitude,
            Long distance,
            Integer orderBy) throws Exception;
}
