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

	public void setItemDao(ItemDao itemDao)
	{
		this.itemDao = itemDao;
	}
	@Override
	public List<ItemCommerce> searchItems(Message message){
		List<Commerce> commercesToKeep = commerceService.getCommercesByDistance(message.getDistance(), message.getLatitude(), message.getLongitude());
		List<ItemCommerce> itemCommerceList = new ArrayList<>();

		if (!commercesToKeep.isEmpty()){
			itemCommerceList = itemDao.getItemCommerceByCategoryOrBrand(message.getCategory(), commercesToKeep);
		}

   		return itemCommerceList;
   }

   @Override
   public boolean createItems() {
	   try{
	   	itemDao.createItems();
	   	return true;
	   }catch(Exception e){
	   	return false;
	   }
   }

   @Override
   public Item searchItemById(Long id){
		return itemDao.getItemById(id);
   }
}
