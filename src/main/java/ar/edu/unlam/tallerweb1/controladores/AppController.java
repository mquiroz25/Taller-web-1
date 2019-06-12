package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Category;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import ar.edu.unlam.tallerweb1.modelo.Message;

import ar.edu.unlam.tallerweb1.servicios.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

@Transactional
@Controller
public class AppController {

    @Inject
    private ItemService itemService;

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelMap model = new ModelMap();

        Message message = new Message();
        model.put("message", message);

        return new ModelAndView("home", model);
    }

    // Busca por categoria, las cargadas son "gaseosas" y "lavandinas"
	// ahora tambien busca por marca, las cargadas son "pepsi" y "ayudin"
    @RequestMapping(path = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute("message") Message message, HttpServletRequest request) {
        ModelMap model = new ModelMap();

	//	Map<Item, List<Commerce>> responseMap = new HashMap<>();
        
        
        List<ItemCommerce> items = itemService.searchItems(message);

	/*	for (ItemCommerce itemCommerce: items) {
			Item itemForMap = itemCommerce.getItem();
			List<Commerce> commerceList = new ArrayList<>();

            if (responseMap.containsKey(itemForMap)){
                commerceList = responseMap.get(itemForMap);
                commerceList.add(itemCommerce.getCommerce());

                responseMap.replace(itemForMap, commerceList);
            } else {
                commerceList.add(itemCommerce.getCommerce());
                responseMap.put(itemForMap, new ArrayList<>());
            }
		}*/

		model.put("items", items);

        return new ModelAndView("itemList", model);
    }

    // Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }

	
	@RequestMapping(path="/cargarProductos",method = RequestMethod.GET)
	public ModelAndView cargarProductos() {
		itemService.crearItems();
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(path="/mostrarEnMapa", method = RequestMethod.GET)
	public ModelAndView mostrarEnMapa(String nombre, Double latitud, Double longitud) {
		ModelMap model = new ModelMap();
		
		Commerce Commerce = new Commerce(nombre, latitud, longitud);

		List<Commerce> list = new ArrayList<>();
		list.add(Commerce);

		// Convierto la lista en una cadena json
		Gson gson = new Gson();
		String jsonString = gson.toJson(list);
		 
		model.put("jsonString", jsonString);

		return new ModelAndView("mostrarEnMapa", model);
	}
	
	@RequestMapping(path="/detalleProducto", method = RequestMethod.GET)
	public ModelAndView detalleProducto(String marca, String descripcion, String imagen,String categoria) {
		ModelMap model = new ModelMap();
		
		Category category=new Category();
		category.setName(categoria);
		
		
		Item item = new Item();
		item.setBrand(marca);
		item.setDescription(descripcion);
		item.setUrlImage(imagen);
		item.setCategory(category);

		model.put("item", item);

		return new ModelAndView("detalleProducto", model);
	}
	
	
}
