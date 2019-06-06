package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemDao;
import ar.edu.unlam.tallerweb1.modelo.Message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService{

    @Inject
    private ItemDao itemDao;

   public List<Object[]> searchItems(Message message){
	   List<Object[]> items = new ArrayList<Object[]>();
	   if (message.getLatitude() != null && message.getLongitude() != null) {
		   items = itemDao.obtainItemsByCategoryAndLocation(message);
	} else {
		items = itemDao.obtainItemsByCategory(message);
	}
	   return items;
    }

}
