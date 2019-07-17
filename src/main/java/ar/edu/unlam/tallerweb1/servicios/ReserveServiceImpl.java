package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ReserveDao;
import ar.edu.unlam.tallerweb1.modelo.Reserve;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

@Service("ReserveService")
@Transactional
public class ReserveServiceImpl implements ReserveService{

	@Inject
	private ReserveDao reserveDao;
	
	@Inject
	private ItemCommerceService itemCommerceService;

	public void setItemCommerceService(ItemCommerceService itemCommerceService) {
		this.itemCommerceService = itemCommerceService;
	}
	
	

	public void setReserveDao(ReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}



	@Override
	public Reserve saveReserve(Reserve reserve) throws Exception {
			Integer stock = itemCommerceService.checkAvailability(reserve.getCommerceId(), reserve.getItemId());
			if (stock - reserve.getAmount() < 0) {
				throw new Exception("No es posible reservar la cantidad ingresada");
			}
			itemCommerceService.deductStock(reserve.getCommerceId(), reserve.getItemId(), reserve.getAmount());
			reserveDao.saveReserve(reserve);
			return reserve;
	}
}
