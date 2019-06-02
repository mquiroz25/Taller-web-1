package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemDao;
import ar.edu.unlam.tallerweb1.modelo.Category;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService{

    @Inject
    private ItemDao itemDao;

   public List<Object[]> searchItems(Message message){
       List<Object[]> items = itemDao.obtainItemsByCategoryAndLocation(message);
        return items;
    }

}
