package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ItemCommerceDao;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static ar.edu.unlam.tallerweb1.modelo.UrlPinMapConstants.*;

@Service("ItemCommerceService")
@Transactional
public class ItemCommerceServiceImpl implements ItemCommerceService {

    @Inject
    private ItemService itemService;

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
	public Integer checkAvailability(Long idCommerce, Long idItem) throws Exception {
        Integer stock = itemCommerceDao.getStock(idCommerce, idItem);
        if (stock <= 0) {
			throw new Exception("El item seleccionado no cuenta con stock");
		}
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

	public ModelMap getModelForView(
	        Long idItem,
            String category,
            Double latitude,
            Double longitude,
            Long distance,
            Integer orderBy) throws Exception
    {
        ModelMap model = new ModelMap();
        Message message = new Message();

        Item item = itemService.searchItemById(idItem);
        if (item == null){
            throw new Exception("Item no encontrado");
        }
        model.put("item", item);

        message.setCategory(category);
        message.setLatitude(latitude);
        message.setLongitude(longitude);
        message.setDistance(distance);

        model.put("latitude", latitude);
        model.put("longitude", longitude);

        List<ItemCommerce> listItemCommerce = itemService.searchItems(message);
        List<ItemCommerceGoogleMaps> list = new ArrayList<>();

        for (ItemCommerce itemCommerce : listItemCommerce) {
            if (itemCommerce.getItem().getId().equals(idItem)) {
                list.add(new ItemCommerceGoogleMaps(itemCommerce));
            }
        }
        list = setIconItemCommerce(list, orderBy);

        model.put("itemCommerce", list);

        return model;
    }

    private List<ItemCommerceGoogleMaps> setIconItemCommerce(List<ItemCommerceGoogleMaps> listItemCommerce, Integer orderBy){

        List<ItemCommerceGoogleMaps> listOrderByPrice = orderItemCommerceListByPrice(new ArrayList<>(listItemCommerce));
        List<ItemCommerceGoogleMaps> listOrderByRanking = orderItemCommerceListByRanking(new ArrayList<>(listItemCommerce));

        for (int i = 0; i < listItemCommerce.size(); i++) {
            listOrderByPrice.get(i).setUrlPinImage(VioletPinConstant);
            listOrderByRanking.get(i).setUrlPinImage(VioletPinConstant);
        }

        // checks if for both list are in the first position, the same element
        if (listOrderByPrice.get(0).getId().equals(listOrderByRanking.get(0).getId())){
            // we have the best price and best ranking, so the image is one in particular
            listOrderByPrice.get(0).setUrlPinImage(GreenAndRedPinConstant);
            listOrderByRanking.get(0).setUrlPinImage(GreenAndRedPinConstant);
        }
        else{
            listOrderByPrice.get(0).setUrlPinImage(GreenPinConstant);
            listOrderByRanking.get(0).setUrlPinImage(RedPinConstant);
        }

        switch (orderBy){
            case 1: // order by price
                return listOrderByPrice;
            case 2: // order by ranking
            default:
                return listOrderByRanking;
        }
    }

    private List<ItemCommerceGoogleMaps> orderItemCommerceListByPrice(List<ItemCommerce> listToOrder){
        List<ItemCommerceGoogleMaps> result = new ArrayList<>();

        Collections.sort(listToOrder, (ItemCommerce ic1, ItemCommerce ic2)
                ->ic1.getPrice().compareTo(ic2.getPrice()));

        for (ItemCommerce itemCommerce : listToOrder) {
            result.add(new ItemCommerceGoogleMaps(itemCommerce));
        }

        return result;
    }

    private List<ItemCommerceGoogleMaps> orderItemCommerceListByRanking(List<ItemCommerce> listToOrder){
        List<ItemCommerceGoogleMaps> result = new ArrayList<>();

        Collections.sort(listToOrder, (ItemCommerce ic1, ItemCommerce ic2)
                ->ic2.getCommerce().getAverageRanking().compareTo(ic1.getCommerce().getAverageRanking()));

        for (ItemCommerce itemCommerce : listToOrder) {
            result.add(new ItemCommerceGoogleMaps(itemCommerce));
        }

        return result;
    }
}
