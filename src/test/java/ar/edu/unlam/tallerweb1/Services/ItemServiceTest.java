package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.ItemDao;
import ar.edu.unlam.tallerweb1.servicios.ItemServiceImpl;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemServiceTest {
    @Test
    public void testQueVerificaQueAlDarErrorCrearItemsDevuelvaFalse(){
        ItemDao itemDao = mock(ItemDao.class);
        doThrow(Exception.class).when(itemDao).createItems();
        ItemServiceImpl sut = new ItemServiceImpl();
        sut.setItemDao(itemDao);
        assertThat(sut.createItems()).isFalse();
    }

}
