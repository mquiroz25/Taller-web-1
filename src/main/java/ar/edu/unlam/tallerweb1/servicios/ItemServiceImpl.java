package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemDao;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService{

    @Inject
    private ItemDao itemDao;

   public List<Object[]> searchItems(Message message){
	   List<Object[]> items = itemDao.obtainItemsByCategoryAndLocation(message);
	   return items;
   }

@Override
public List<Item> obtenerProductoPorMarca(String marca) {
	return itemDao.obtenerProductoPorMarca(marca);

}

@Override
public void crearItems() {
itemDao.crearItems();
	
}



}
