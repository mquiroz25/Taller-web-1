package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.ReserveDao;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.servicios.ItemCommerceService;
import ar.edu.unlam.tallerweb1.servicios.ReserveServiceImpl;

import org.junit.Test;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ReserveServiceTest {

	@Test (expected = Exception.class)
	public void testQueLanzaExceptionSiLaCantidadEsMayorAlStock() throws Exception
	{
		Reserve reserve = mock(Reserve.class);
		ReserveDao reserveDao = mock(ReserveDao.class);
		ItemCommerceService itemComerceService = mock(ItemCommerceService.class);
		
		when(reserve.getAmount()).thenReturn(100);
		when(itemComerceService.checkAvailability(anyLong(), anyLong())).thenReturn(10);
		
		ReserveServiceImpl reserveServiceImp = new ReserveServiceImpl();

		reserveServiceImp.setItemCommerceService(itemComerceService);
		reserveServiceImp.setReserveDao(reserveDao);
	
		reserveServiceImp.saveReserve(reserve);

	}
	
	@Test
	public void testQueVerificaSiElStockEsCorrectoYGuardaReserva() throws Exception
	{
		Reserve reserve = mock(Reserve.class);
		ReserveDao reserveDao = mock(ReserveDao.class);
		ItemCommerceService itemComerceService = mock(ItemCommerceService.class);
		
		when(reserve.getAmount()).thenReturn(5);
		when(itemComerceService.checkAvailability(anyLong(), anyLong())).thenReturn(10);
		
		ReserveServiceImpl reserveServiceImp = new ReserveServiceImpl();

		reserveServiceImp.setItemCommerceService(itemComerceService);
		reserveServiceImp.setReserveDao(reserveDao);
	
		Reserve savedReserve = reserveServiceImp.saveReserve(reserve);
		assertThat(savedReserve).isEqualTo(reserve);

	}
}
