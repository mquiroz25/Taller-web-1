package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.Message;

import java.util.List;

public interface ItemDao {
   List<ItemCommerce> getItemCommerceByCategory(String category,  List<Commerce> commercesToKeep);
   List<Item> obtenerProductoPorMarca(String marca);
   
   void crearItems();

}

