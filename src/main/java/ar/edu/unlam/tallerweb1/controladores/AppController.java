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
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession(true);
        List<ItemCommerce> itemCommerces = itemService.searchItems(message);
        Message messageForm = new Message();
        model.put("message", messageForm);

        session.setAttribute("items", itemCommerces);

        List <Item> items = new ArrayList<>();
        for (ItemCommerce var : itemCommerces) {
            if (!items.contains(var.getItem())) {
                items.add(var.getItem());
            }
        }
        model.put("items", items);

        return new ModelAndView("itemList", model);
    }

    // Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }


    @RequestMapping(path = "/cargarProductos", method = RequestMethod.GET)
    public ModelAndView cargarProductos() {
        itemService.crearItems();
        return new ModelAndView("redirect:/home");
    }


    @RequestMapping(path = "/detalleProducto", method = RequestMethod.POST)
    public ModelAndView detalleProducto(@ModelAttribute("message") Message message, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Item item = itemService.searchItemById(message.getIdItem());
        model.put("item", item);

        //para el mapa
        HttpSession session= request.getSession();
        List<ItemCommerce> auxList = (List<ItemCommerce>) session.getAttribute("items");

        List<ItemCommerce> list = new ArrayList<>();
        for (ItemCommerce itemCommerce:auxList) {
        if (itemCommerce.getItem().getId().equals(message.getIdItem())) {
            list.add(itemCommerce);
            }
        }

        model.put("itemCommerce", list);

        return new ModelAndView("detalleProducto", model);
    }
}
