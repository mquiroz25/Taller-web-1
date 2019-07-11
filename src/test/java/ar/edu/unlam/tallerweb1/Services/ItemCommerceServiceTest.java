package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.ItemCommerceDaoImpl;
import ar.edu.unlam.tallerweb1.servicios.ItemCommerceServiceImpl;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ItemCommerceServiceTest {

    @Test
    public void testQueVerificaQuegetItemCommerceByIdSeEjecute(){
        ItemCommerceDaoImpl itemCommerceDao = mock(ItemCommerceDaoImpl.class);
        ItemCommerceServiceImpl sut = new ItemCommerceServiceImpl();
        sut.setCommerceDao(itemCommerceDao);
        sut.notifyNoStock(1L, 1L);
        verify(itemCommerceDao, times(1)).getItemCommerceById(1L, 1L);
    }

}
