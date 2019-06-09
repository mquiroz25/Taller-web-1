package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Category;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("ItemDao")
public class ItemDaoImpl implements ItemDao {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<Item> obtainItemsByCategoryAndLocation(Message message){
        final Session session = sessionFactory.getCurrentSession();

        @SuppressWarnings("unchecked")
		List<Item> lista_items = session.createCriteria(Item.class)
        		.createAlias("category", "c")
        		.add(Restrictions.ilike("c.name", message.getCategory(), MatchMode.ANYWHERE))
        		.list();

        return lista_items;
    }
    
    
    @Override
	public List<Item> obtenerProductoPorMarca(String marca) {
    	final Session session = sessionFactory.getCurrentSession();
		
    	@SuppressWarnings("unchecked")
    	List<Item> productos = session.createCriteria(Item.class)
    		.add(Restrictions.eq("brand", marca))
    		.list();
		   
    	return productos;
    }


	@Override
	public void crearItems() {
        final Session session = sessionFactory.getCurrentSession();
        
		Category lavandinas = new Category();
		lavandinas.setName("lavandinas");
		session.save(lavandinas);
		
		Category gaseosas = new Category();
		gaseosas.setName("gaseosas");
		session.save(gaseosas);
		
		Commerce walmart=new Commerce();
		walmart.setName("walmart");
		walmart.setLatitude(-34.652984000000004);
		walmart.setLongitude(-58.680162100000004);
		
		Commerce jumbo=new Commerce();
		jumbo.setName("jumbo");
		jumbo.setLatitude(-34.696100213550224);
		jumbo.setLongitude(-58.562610974620014);
		
		Commerce coto=new Commerce();
		coto.setName("coto");
		coto.setLatitude(-34.694094417308314);
		coto.setLongitude(-58.57249123051896);
		
		
		Commerce carrefour=new Commerce();
		carrefour.setName("carrefour");
		carrefour.setLatitude(-34.709537794286426);
		carrefour.setLongitude(-58.54431420444564);
		
		Set<Commerce>commerces=new HashSet<>();
		commerces.add(walmart);
		commerces.add(jumbo);
		commerces.add(coto);
		
		Set<Commerce>commerces1=new HashSet<>();
		commerces1.add(carrefour);
		commerces1.add(coto);	
			
		
        Item ayudin =new Item();
        ayudin.setBrand("ayudin");
		ayudin.setUrlImage("https://perfumeriaspigmento.com.ar/media/catalog/product/cache/image/620x678/e9c3970ab036de70892d86c6d221abfe/7/9/79879.jpg");
		ayudin.setDescription("AYUDIN LAVANDINA ANDINA MULTISUP ORIGIN 2 Litros");
		ayudin.setCategory(lavandinas);
		ayudin.setCommerces(commerces);
		session.save(ayudin);
		
	    Item ayudin1 =new Item();
        ayudin1.setBrand("ayudin");
		ayudin1.setUrlImage("http://www.servibol.com.ar/image/stores/33/cache/data/lavandina-ayudin-doble-rendimiento-bot-4l-cleansytec-23184-MLA20243633005_022015-O-500x500.jpg");
		ayudin1.setDescription("AYUDIN triple accion 1 litro");
		ayudin1.setCategory(lavandinas);
		ayudin1.setCommerces(commerces1);
		session.save(ayudin1);

		Item pepsi =new Item();
		pepsi.setBrand("pepsi");
		pepsi.setUrlImage("https://www.superama.com.mx/Content/images/products/img_large/0750103131001L.jpg");
		pepsi.setDescription("Gaseosa Pepsi 1,5 Lt");
		pepsi.setCategory(gaseosas);
		pepsi.setCommerces(commerces1);
		session.save(pepsi);		
	}

}
