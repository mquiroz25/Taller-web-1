package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;

import java.util.List;

public interface ItemDao {
   List<ItemCommerce> getItemCommerceByCategoryOrBrand(String category,  List<Commerce> commercesToKeep);
   Item getItemById(Long id);
   void createItems();
}