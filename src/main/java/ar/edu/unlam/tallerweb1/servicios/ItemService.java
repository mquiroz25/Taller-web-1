package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;

import java.util.List;

public interface ItemService {
   List<Object[]> searchItems(Message message);
   List<Item> obtenerProductoPorMarca(String marca);
   void crearItems();

}
