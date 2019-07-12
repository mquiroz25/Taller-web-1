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

    public void setCommerceDao(ItemCommerceDao itemCommerceDao)
    {
        this.itemCommerceDao = itemCommerceDao;
    }

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

	@Override
	public Integer checkStock(Long idCommerce, Long idItem) {
        Integer stock = itemCommerceDao.getStock(idCommerce, idItem);
        return stock;
	}

	@Override
	public ItemCommerce getItemCommerceById(Long idCommerce, Long idItem) {
		ItemCommerce itemCommerce = itemCommerceDao.getItemCommerceById(idCommerce, idItem);
		return itemCommerce;
	}

	@Override
	public void deductStock(Long idCommerce, Long idItem, Integer amount) {
		itemCommerceDao.deductStock(idCommerce, idItem, amount);
	}
}
