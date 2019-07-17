package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.Message;

import java.util.List;

public interface ItemService {
   List<ItemCommerce> searchItems(Message message);
   boolean createItems();
   Item searchItemById(Long id);
}
