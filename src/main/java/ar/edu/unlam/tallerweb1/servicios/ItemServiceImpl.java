package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemDao;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService{

    @Inject
    private ItemDao itemDao;

   @Override
   public List<Item> searchItems(Message message){ 
	   List<Item> lista_items = itemDao.obtainItemsByCategoryAndLocation(message);

	   if (message.getDistancia() != null) {
		   Iterator<Item> items = lista_items.iterator();
		   while (items.hasNext()) {
			   Item item = items.next();
			   Set<Commerce> lita_comercios = item.getCommerces();
			   Iterator<Commerce> commerces = lita_comercios.iterator();
			   while (commerces.hasNext()) {
				   Commerce commerce = commerces.next();			    	  
				   final Long distancia = Math.round((Math.acos(((Math.sin(Math.toRadians(commerce.getLatitude())))*(Math.sin(Math.toRadians(message.getLatitude())))) + ((Math.cos(Math.toRadians(commerce.getLatitude())))*(Math.cos(Math.toRadians(message.getLatitude())))*(Math.cos(Math.toRadians(message.getLongitude()-commerce.getLongitude()))))) * 6371));
				   if (distancia > message.getDistancia()) {
					   commerces.remove();
				   }
			   }
			   if (item.getCommerces().isEmpty()) {
				   items.remove();
				}
		   }
	   } else {
		   Set<Set<Commerce>> lista_comercios = new HashSet<>();
		   
		   for (Item item : lista_items) {
			   lista_comercios.add(item.getCommerces());
		}
	}
       return lista_items;
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
