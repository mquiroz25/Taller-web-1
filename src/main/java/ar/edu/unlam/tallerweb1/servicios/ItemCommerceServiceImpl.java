package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemCommerceDao;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("ItemCommerceService")
@Transactional
public class ItemCommerceServiceImpl implements ItemCommerceService {

    @Inject
    private ItemCommerceDao itemCommerceDao;

    @Override
    public boolean notifyNoStock(Long idCommerce, Long idItem){
        try{
            ItemCommerce itemCommerce = itemCommerceDao.getItemCommerceById(idCommerce, idItem);
            itemCommerce.setStock(0);
            itemCommerceDao.notifyNoStock(itemCommerce);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
