package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemDao;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.Message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.*;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService{

	@Inject
	private ItemDao itemDao;

	@Inject
	private CommerceService commerceService;

	@Override
	public List<ItemCommerce> searchItems(Message message){
		List<Commerce> commercesToKeep = commerceService.getCommercesByDistance(message.getDistancia(), message.getLatitude(), message.getLongitude());
		List<ItemCommerce> itemCommerceList = itemDao.getItemCommerceByCategoryOrBrand(message.getCategory(), commercesToKeep);

   		return itemCommerceList;
   }

   private List<Item> getItemsByDistance(List<ItemCommerce> itemCommercesToFilter, Message message){
		List<Item> itemsFiltered = new ArrayList<>();

		for (ItemCommerce itemCommerce: itemCommercesToFilter) {
		   Commerce commerce = itemCommerce.getCommerce();

		   if (message.getDistancia() < calculateDistanceBetweenCommerceAndUser(message.getLatitude(), message.getLongitude(), commerce.getLatitude(), commerce.getLongitude())){
		   		itemsFiltered.add(itemCommerce.getItem());
		   }
	   }

		return itemsFiltered;
   }

   private Long calculateDistanceBetweenCommerceAndUser(Double LatUser, Double LonUser, Double LatCommerce, Double LonCommerce){
		return calculateDistance(LatUser, LonUser, LatCommerce, LonCommerce);
   }

   private Long calculateDistance(Double Lat1, Double Lon1, Double Lat2, Double Lon2){
	   return Math.round((Math.acos(((Math.sin(Math.toRadians(Lat2)))*(Math.sin(Math.toRadians(Lat1)))) + ((Math.cos(Math.toRadians(Lat2)))*(Math.cos(Math.toRadians(Lat1)))*(Math.cos(Math.toRadians(Lon1 - Lon2))))) * 6371));
   }

   @Override
   public void crearItems() {
	   itemDao.crearItems();
   }

   @Override
   public Item searchItemById(Long id){
		return itemDao.getItemById(id);
   }

}
