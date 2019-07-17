package ar.edu.unlam.tallerweb1.services;

import ar.edu.unlam.tallerweb1.dao.ItemCommerceDaoImpl;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.servicios.ItemCommerceServiceImpl;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemCommerceServiceTest {

    @Test
    public void testQueVerificaQuegetItemCommerceByIdSeEjecute(){
        ItemCommerceDaoImpl itemCommerceDao = mock(ItemCommerceDaoImpl.class);
        when(itemCommerceDao.getItemCommerceById(anyLong(), anyLong())).thenReturn(new ItemCommerce());
        ItemCommerceServiceImpl sut = new ItemCommerceServiceImpl();
        sut.setCommerceDao(itemCommerceDao);
        assertThat(sut.notifyNoStock(1L, 1L)).isTrue();
    }
    
    @Test
    public void testQueVerificaQuegetItemCommerceByIdNoDevuelveResultadosDevuelvaFalse(){
        ItemCommerceDaoImpl itemCommerceDao = mock(ItemCommerceDaoImpl.class);
        when(itemCommerceDao.getItemCommerceById(anyLong(), anyLong())).thenReturn(null);
        
        ItemCommerceServiceImpl sut = new ItemCommerceServiceImpl();
        sut.setCommerceDao(itemCommerceDao);
        assertThat(sut.notifyNoStock(1L, 1L)).isFalse();
    }

    @Test
    public void testQueVerificaQueGetItemEnCasoDeErrorDevuelvaFalse(){
        ItemCommerceDaoImpl itemCommerceDao = mock(ItemCommerceDaoImpl.class);
        when(itemCommerceDao.getItemCommerceById(anyLong(), anyLong())).thenThrow(Exception.class);
        ItemCommerceServiceImpl sut = new ItemCommerceServiceImpl();
        sut.setCommerceDao(itemCommerceDao);
        assertThat(sut.notifyNoStock(1L, 1L)).isFalse();
    }

    @Test
    public void testQueVerificaQueNotificarStockEnCasoDeErrorDevuelvaFalse(){
        ItemCommerceDaoImpl itemCommerceDao = mock(ItemCommerceDaoImpl.class);
        when(itemCommerceDao.getItemCommerceById(anyLong(), anyLong())).thenReturn(new ItemCommerce());
        
        doThrow(Exception.class).when(itemCommerceDao).notifyNoStock(any(ItemCommerce.class));
        
        ItemCommerceServiceImpl sut = new ItemCommerceServiceImpl();
        sut.setCommerceDao(itemCommerceDao);
        assertThat(sut.notifyNoStock(1L, 1L)).isFalse();
    }
}
