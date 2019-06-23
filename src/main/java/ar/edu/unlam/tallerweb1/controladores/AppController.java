package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerceTransporter;
import ar.edu.unlam.tallerweb1.modelo.Message;
import ar.edu.unlam.tallerweb1.modelo.Ranking;
import ar.edu.unlam.tallerweb1.servicios.CommerceService;
import ar.edu.unlam.tallerweb1.servicios.ItemService;
import ar.edu.unlam.tallerweb1.servicios.RankingService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        
        ItemCommerceTransporter.setItemscommerces(itemCommerces);
           
        model.put("items", items);

        return new ModelAndView("itemList", model);
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

        List<ItemCommerce>listItemCommerce=ItemCommerceTransporter.getItemscommerces();
        List <Commerce>CommerceList= new ArrayList<>();
        List<ItemCommerce> list = new ArrayList<>();
        
        for (ItemCommerce itemCommerce:listItemCommerce) {
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
    
    
    
    @RequestMapping(path = "/qualify/{id_commerce}/{name_commerce}", method = RequestMethod.GET)
    public ModelAndView calificar(@PathVariable Long id_commerce,@PathVariable String name_commerce ) {
    	
        ModelMap model = new ModelMap();

        //obtengo la lista de ranking por id delcomercio
        List<Ranking>rankingListCommerce=new ArrayList<>();
        
        rankingListCommerce= rankingService.getRankingByIdCommerce(id_commerce);
        
        model.put("rankingListCommerce", rankingListCommerce);
        model.put("id_commerce", id_commerce);    
        model.put("name_commerce", name_commerce);
  
        return new ModelAndView("qualify", model);
    }
  
    
    
    @RequestMapping(path ="/processQualification", method = RequestMethod.GET)
    public ModelAndView procesar(Long id,Double attention,Double speed,Double prices,String review) {
 
        ModelMap model = new ModelMap();
        
        Double av = (attention+ speed + prices)/3;
    	double avOneDecimal = Math.round(av * 10.0) / 10.0;
        
       //obtengo el comercio con el id
        Commerce commerce =new Commerce();
        commerce=commerceService.getCommerceById(id);
        
        //recibo la calificacion y la guardo en un objeto ranking
        Ranking ranking =new Ranking();
        ranking.setValue(avOneDecimal);
        ranking.setReview(review);
        
        //seteo el ranking al comercio
        ranking.setCommerce(commerce);
        
        rankingService.saveRanking(ranking);
  
        //obtengo la lista de ranking por id delcomercio
        List<Ranking>rankingList=new ArrayList<>();
        rankingList= rankingService.getRankingByIdCommerce(id);
        Double averageRanking=rankingService.getAverageRanking(rankingList);

		double roundedDouble = Math.round(averageRanking * 10.0) / 10.0;

        commerce.setAverageRanking(roundedDouble);
 
        return new ModelAndView("redirect:/home");   
    }
  
}
