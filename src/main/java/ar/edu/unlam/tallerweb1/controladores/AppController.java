package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.Message;
import ar.edu.unlam.tallerweb1.modelo.Ranking;
import ar.edu.unlam.tallerweb1.servicios.CommerceService;
import ar.edu.unlam.tallerweb1.servicios.ItemCommerceService;
import ar.edu.unlam.tallerweb1.servicios.ItemService;
import ar.edu.unlam.tallerweb1.servicios.RankingService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;
import java.util.*;

@Transactional
@Controller
public class AppController {

    @Inject
    private ItemService itemService;
    @Inject
    private CommerceService commerceService;
    @Inject
    private RankingService rankingService;
    @Inject
    private ItemCommerceService itemCommerceService;


    public void setCommerceService(CommerceService commerceService) {
		this.commerceService = commerceService;
	}

	public void setRankingService(RankingService rankingService) {
		this.rankingService = rankingService;
	}


	@RequestMapping(path = "/loadProducts", method = RequestMethod.GET)
    public ModelAndView loadProducts() {
        itemService.createItems();
        return new ModelAndView("redirect:/home");
    }
    
    
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelMap model = new ModelMap();

        Message message = new Message();
        model.put("message", message);

        return new ModelAndView("home", model);
    }

    // Busca por categoria, las cargadas son "gaseosas" y "lavandinas"
    // ahora tambien busca por marca, las cargadas son "pepsi" y "ayudin"
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("message") Message message) {
        ModelMap model = new ModelMap();
        
        List<ItemCommerce> itemCommerces = itemService.searchItems(message);
        
        	model.put("m", message);
  
        List <Item> items = new ArrayList<>();
        for (ItemCommerce var : itemCommerces) {
            if (!items.contains(var.getItem())) {
                items.add(var.getItem());
            }
        }
           
        model.put("items", items);

        return new ModelAndView("itemList", model);
    }

    @RequestMapping(path = "/productDetail", method = RequestMethod.GET)
    public ModelAndView productDetail(@RequestParam Long idItem,@RequestParam String category,@RequestParam Double latitude,@RequestParam Double longitude,@RequestParam Long distance) {
        ModelMap model = new ModelMap();
        Item item = itemService.searchItemById(idItem);
        model.put("item", item);

        Message message = new Message();
        message.setCategory(category);
        message.setLatitude(latitude);
        message.setLongitude(longitude);
        message.setDistance(distance);
        
        model.put("latitude", latitude);
        model.put("longitude", longitude);
        
        List<ItemCommerce> listItemCommerce = itemService.searchItems(message);
        List<ItemCommerce> list = new ArrayList<>();
        
        for (ItemCommerce itemCommerce : listItemCommerce) {
            if (itemCommerce.getItem().getId().equals(idItem)) {
                list.add(itemCommerce);
            }
        }

        model.put("itemCommerce", list); 

        return new ModelAndView("productDetail", model);
    }

    @RequestMapping(path = "/rate/{id_commerce}/{name_commerce}", method = RequestMethod.GET)
    public ModelAndView rate(@PathVariable Long id_commerce,@PathVariable String name_commerce ) {
        ModelMap model = new ModelMap();

        //obtengo la lista de ranking por id delcomercio
        List<Ranking> rankingListCommerce = rankingService.getRankingByIdCommerce(id_commerce);
        
        model.put("rankingListCommerce", rankingListCommerce);
        model.put("id_commerce", id_commerce);    
        model.put("name_commerce", name_commerce);
  
        return new ModelAndView("rating", model);
    }

    @RequestMapping(path ="/processRating", method = RequestMethod.GET)
    public ModelAndView process(Long id, Double attention, Double speed, Double prices, String review) {	
    	
    	ModelMap model = new ModelMap();
    	
        Double averageForCriteria = rankingService.getAverageForCriteria(attention, speed, prices);
        
        //guardo la calificacion obtenida en un objeto ranking
        Ranking ranking = new Ranking();
        ranking.setValue(averageForCriteria);
        ranking.setReview(review);
        
        //obtengo el comercio con el id
        Commerce commerce = commerceService.getCommerceById(id);
        
        	if(commerce!=null) { 	
            //seteo el ranking al comercio
            ranking.setCommerce(commerce);
            rankingService.saveRanking(ranking);
      
            //obtengo la lista de ranking por id delcomercio
            List<Ranking> rankingList = rankingService.getRankingByIdCommerce(id);
            commerce.setAverageRanking(rankingList);

            return new ModelAndView("redirect:/home");  
        }
    
        else {
        	model.put("error", "no existe comercio con ese id");
            return new ModelAndView("error", model);
        }
    }


    @RequestMapping(path ="/noStock", method = RequestMethod.GET, produces = "application/json")
    public ModelAndView noStock(@RequestParam Long idCommerce, @RequestParam Long idItem) {
        boolean param = itemCommerceService.notifyNoStock(idCommerce, idItem);
        ModelMap model = new ModelMap();
        if(!param){
            model.put("error", "Hubo un error al notificar stock, vuelva a intentar más tarde");
            return new ModelAndView("error", model);
        }else{
            return new ModelAndView("redirect:/home");
        }
    }

    
    // Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("redirect:/home");
    }
    
    
}
