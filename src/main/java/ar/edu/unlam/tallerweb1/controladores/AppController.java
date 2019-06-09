package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @RequestMapping(path = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute("message") Message message, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        List<Object[]> items = itemService.searchItems(message);
        model.put("items", items);
        return new ModelAndView("itemList", model);
    }


    // Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }
    
    

    //ayudin o pepsi
	@RequestMapping(path="/buscarProductoPorMarca",method=RequestMethod.GET)
	public ModelAndView buscarProducto() {
		
		
		itemService.crearItems();
		

		return new ModelAndView("buscar");
	}
    
/*	@RequestMapping(path="/ComerciosEncontrados",method=RequestMethod.GET)
	public ModelAndView detalleProducto(@RequestParam String marca) throws UnsupportedEncodingException {
		
		
		  ModelMap model = new ModelMap();
		
		List<Item>listaDeProductos=itemService.obtenerProductoPorMarca(marca);
		
		
		//obtengo los comercios del primer producto de la lista
				Set<Commerce>comercios=	 listaDeProductos.get(0).getCommerces();
				
				//convierte set en list
				List <Commerce> comerciosList = new ArrayList <Commerce> (comercios);
				// Convierto la lista en una cadena json
				Gson gson = new Gson();
				String jsonString = gson.toJson(comerciosList);
				

				  model.put("items", listaDeProductos);
				  model.put("comercios", comerciosList);
					//  model.put("jsonString", jsonString); //codifico los espacios de la cadena json para luego poder pasarlo como parametro por url
				    String encode= URLEncoder.encode(jsonString,"UTF-8");
				    
				    model.put("encode", encode);
				    
				    
				return new ModelAndView("itemList",model);
					//	return new ModelAndView("resultado",model);

	}*/
	
	
	
 
	@RequestMapping(path="/ProductosEncontrados",method=RequestMethod.GET)
	public ModelAndView detalleProducto(@RequestParam String marca) {
		
		
		  ModelMap model = new ModelMap();
		
		List<Item>listaDeProductos=itemService.obtenerProductoPorMarca(marca);
		
		
Set<Set<Commerce>> comerciosList= new HashSet<>();


		for (Item p:listaDeProductos) {
			
			comerciosList.add(p.getCommerces());

			}
		



				  model.put("items", listaDeProductos);
		
		

				return new ModelAndView("itemList",model);

	}
	


	@RequestMapping(path="/mostrarEnMapa",method=RequestMethod.GET)
	public ModelAndView mostrarEnMapa(String nombre,Double latitud,Double longitud) {
		
		 ModelMap model = new ModelMap();
		 
		Commerce  Commerce=new Commerce();

		
		
		Commerce.setName(nombre);
		Commerce.setLatitude(latitud);
		Commerce.setLongitude(longitud);
		

		
		
		
List<Commerce>list=new ArrayList<>();
list.add(Commerce);



		// Convierto la lista en una cadena json
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
		 
		 
		 
		 
		model.put("jsonString", jsonString);

		return new ModelAndView("mostrarEnMapa",model);
	}
 
}
