package ar.edu.unlam.tallerweb1.Controllers;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.Message;
import ar.edu.unlam.tallerweb1.servicios.ItemService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.AppController;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;
import ar.edu.unlam.tallerweb1.servicios.CommerceService;
import ar.edu.unlam.tallerweb1.servicios.RankingService;

import static org.assertj.core.api.Assertions.assertThat;

public class AppControllerProcessRatingTest {
	
	@Test
	public void testQueVerificaQueComercioExistaYRedirijaAhome()
	{
		
		AppController sut = new AppController ();
		
		RankingService rankingServiceMock = mock(RankingService.class);	
		CommerceService commerceServiceMock = mock(CommerceService.class);
		Commerce commerceMock = mock(Commerce.class);
		
		sut.setCommerceService(commerceServiceMock);
		sut.setRankingService(rankingServiceMock);
		

		Double speed = 5.0;
		Double prices = 5.0;
		Double attention = 5.0;
		Long id_commerce = 1l;
		String review = "buena atencion";

		when(commerceServiceMock.getCommerceById(anyLong())).thenReturn(new Commerce());
		when(rankingServiceMock.getAverageForCriteriaAndSetRankingToCommerce(attention, speed, prices,review,commerceMock)).thenReturn(new Ranking());
		when(rankingServiceMock.getRankingListByIdCommerce(id_commerce)).thenReturn(new ArrayList<Ranking>());
		
		ModelAndView mav = sut.process(id_commerce, attention, speed, prices, review);
		
		assertThat(mav.getViewName()).isEqualTo("redirect:/home");
		
		
	}	
	
	@Test
	public void testQueVerificaQueComercioNoExisteYRedirijeAvistaAerrorConMensajedeError()
	{
		
		AppController sut = new AppController ();
		
		RankingService rankingServiceMock = mock(RankingService.class);	
		CommerceService commerceServiceMock = mock(CommerceService.class);

		sut.setCommerceService(commerceServiceMock);
		sut.setRankingService(rankingServiceMock);

		Double speed = 5.0;
		Double prices = 5.0;
		Double attention = 5.0;
		Long id_commerce = 1l;
		String review = "buena atencion";
		
		when(commerceServiceMock.getCommerceById(id_commerce)).thenReturn(null);

		ModelAndView mav = sut.process(id_commerce, attention, speed, prices, review);
		
		assertThat(mav.getViewName()).isEqualTo("error");
		assertThat(mav.getModel().containsKey("error"));
		assertThat(mav.getModel().get("error")).isEqualTo("no existe comercio con ese id");

	}

	@Test
	public void testQueDeberiaTraerUnaListaDeItemCommerce(){
		ItemService itemService = mock(ItemService.class);
		List<ItemCommerce> itemCommerces = new ArrayList<ItemCommerce>();
		Message message = mock(Message.class);
		when(itemService.searchItems(message)).thenReturn(itemCommerces);
		AppController sut = new AppController();
		sut.setItemService(itemService);
		ModelAndView model =  sut.search(message);
		assertThat(model.getModel().get("items")).isEqualTo(itemCommerces);
	}
	
	
	
}
