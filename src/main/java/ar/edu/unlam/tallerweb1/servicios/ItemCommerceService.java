package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import org.springframework.ui.ModelMap;

public interface ItemCommerceService {
    boolean notifyNoStock(Long idCommerce, Long idItem);
    ItemCommerce getItemCommerceById(Long idCommerce, Long idItem);
    ModelMap getModelForView(
            Long idItem,
            String category,
            Double latitude,
            Double longitude,
            Long distance,
            Integer orderBy) throws Exception;
    Integer checkAvailability(Long idCommerce, Long idItem) throws Exception;
    void deductStock(Long idCommerce, Long idItem, Integer amount) throws Exception;
}
