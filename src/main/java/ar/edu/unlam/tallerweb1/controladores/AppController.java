package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dao.ItemCommerceDao;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerceTransporter;
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
import com.google.gson.Gson;
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
        
        Message messageForm = new Message();
        model.put("message", messageForm);

        List <Item> items = new ArrayList<>();
        for (ItemCommerce var : itemCommerces) {
            if (!items.contains(var.getItem())) {
                items.add(var.getItem());
            }
        }
        
        ItemCommerceTransporter.setItemsCommerces(itemCommerces);
           
        model.put("items", items);

        return new ModelAndView("itemList", model);
    }

    
    @RequestMapping(path = "/procesar", method = RequestMethod.POST)
    public ModelAndView procesar (@ModelAttribute("message") Message message) {
    	
        ModelMap model = new ModelMap();
        model.put("message", message);

        return new ModelAndView("vista", model);
    }

    
    // Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/loadProducts", method = RequestMethod.GET)
    public ModelAndView loadProducts() {
        itemService.createItems();
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path = "/productDetail", method = RequestMethod.POST)
    public ModelAndView productDetail(@ModelAttribute("message") Message message) {
        ModelMap model = new ModelMap();
        Item item = itemService.searchItemById(message.getIdItem());
        model.put("item", item);

        List<ItemCommerce> listItemCommerce = ItemCommerceTransporter.getItemsCommerces();
        List<Commerce> CommerceList = new ArrayList<>();
        List<ItemCommerce> list = new ArrayList<>();
        
        for (ItemCommerce itemCommerce : listItemCommerce) {
            if (itemCommerce.getItem().getId().equals(message.getIdItem())) {
                list.add(itemCommerce);
                CommerceList.add(new Commerce(itemCommerce.getCommerce().getName(), itemCommerce.getCommerce().getLatitude(), itemCommerce.getCommerce().getLongitude()));
            }
        }

		// Convierto la lista en una cadena json
		Gson gson = new Gson();
		String jsonString = gson.toJson(CommerceList);

        model.put("itemCommerce", list); 
        model.put("jsonString", jsonString);

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
        Double averageForCriteria = rankingService.getAverageForCriteria(attention, speed, prices);
        
        //guardo la calificacion obtenida en un objeto ranking
        Ranking ranking = new Ranking();
        ranking.setValue(averageForCriteria);
        ranking.setReview(review);
        
        //obtengo el comercio con el id
        Commerce commerce = commerceService.getCommerceById(id);
        
        //seteo el ranking al comercio
        ranking.setCommerce(commerce);
        
        rankingService.saveRanking(ranking);
  
        //obtengo la lista de ranking por id delcomercio
        List<Ranking> rankingList = rankingService.getRankingByIdCommerce(id);
        commerce.setAverageRanking(rankingList);

        return new ModelAndView("redirect:/home");   
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

}
