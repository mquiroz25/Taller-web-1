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

public class AppControllerTest {
	
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
		when(rankingServiceMock.saveRanking(any(Ranking.class))).thenReturn(true);
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
	public void testQueMuestaErrorCuandoHuboErrorAlGuardarRanking()
	{

		RankingService rankingServiceMock = mock(RankingService.class);
		CommerceService commerceServiceMock = mock(CommerceService.class);

		when(commerceServiceMock.getCommerceById(anyLong())).thenReturn(new Commerce());
		when(rankingServiceMock.saveRanking(any(Ranking.class))).thenReturn(false);
		AppController sut = new AppController ();
		sut.setCommerceService(commerceServiceMock);
		sut.setRankingService(rankingServiceMock);
		ModelAndView mav = sut.process(1L, 2.0, 20.0, 2.0, "asd");

		assertThat(mav.getViewName()).isEqualTo("error");
		assertThat(mav.getModel().containsKey("error"));
		assertThat(mav.getModel().get("error")).isEqualTo("Hubo un error al guardar el ranking");

	}

	
}
