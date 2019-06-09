package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    // sql native porque no sé si sea posible hacerlo con criteria, yo no pude y estuve como 3 horas (?
    // así que GG. Si a alguno se le ocurre como implementarlo bien, sino no importa.
    // SELECT commerce.name, i.brand, category.name, ( 6371 * acos( cos( radians(-34.60362) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(-58.684985) ) + sin( radians(-34.60362) ) * sin( radians( latitude ) ) ) ) AS distance FROM commerce INNER JOIN commerce_item as ci ON commerce.id = ci.commerce_id INNER JOIN item as i ON ci.item_id = i.id INNER JOIN category ON i.category_id = category.id WHERE category.name LIKE "%galletita%" HAVING distance < 20

    @Override
    public List<Object[]> obtainItemsByCategoryAndLocation(Message message){
        final Session session = sessionFactory.getCurrentSession();

        final String sql = "SELECT i.id, Commerce.name as commerce, i.brand as itemBrand, Category.name as category, ROUND((ACOS(((SIN(radians(latitude)))*(SIN(radians("+message.getLatitude()+")))) + ((COS(radians(latitude)))*(COS(radians("+message.getLatitude()+")))*(COS(radians("+message.getLongitude()+"-longitude))))) * 6371), 2) AS distance " +
                "FROM Commerce " +
        		"INNER JOIN commerce_item as ci ON Commerce.id = ci.commerce_id " +
                "INNER JOIN Item as i ON ci.item_id = i.id "+
        		"INNER JOIN Category ON i.category_id = Category.id " +
                "WHERE Category.name LIKE  '%"+message.getCategory()+"%' " +
                "HAVING distance < 10";
        SQLQuery query = session.createSQLQuery(sql);
        @SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
        return rows;

    }
    
    
	public List<Item> obtenerProductoPorMarca(String marca) {
		
		   final Session session = sessionFactory.getCurrentSession();
		
		   List<Item> productos = session.createCriteria(Item.class)
				   .add(Restrictions.eq("brand", marca))
				   .list();
		   
		   
		return productos;
		   
		
		
	}


	@Override
	public void crearItems() {
        final Session session = sessionFactory.getCurrentSession();
        
        Item ayudin =new Item();
        ayudin.setBrand("ayudin");
		ayudin.setUrlImage("https://perfumeriaspigmento.com.ar/media/catalog/product/cache/image/620x678/e9c3970ab036de70892d86c6d221abfe/7/9/79879.jpg");
		ayudin.setDescription("AYUDIN LAVANDINA ANDINA MULTISUP ORIGIN 2 Litros");
		
		
	    Item ayudin1 =new Item();
        ayudin1.setBrand("ayudin");
		ayudin1.setUrlImage("http://www.servibol.com.ar/image/stores/33/cache/data/lavandina-ayudin-doble-rendimiento-bot-4l-cleansytec-23184-MLA20243633005_022015-O-500x500.jpg");
		ayudin1.setDescription("AYUDIN triple accion 1 litro");
		
		
		
		
		
		Commerce walmart=new Commerce();
		walmart.setName("walmart");
		walmart.setLatitude(-34.67624068932531);
		walmart.setLongitude(-58.55517028807671);
		
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
		
		
	
		ayudin.setCommerces(commerces);
	
		
		session.save(ayudin);
		session.save(ayudin1);
		
		


		Item pepsi =new Item();
		pepsi.setBrand("pepsi");
		pepsi.setUrlImage("https://www.superama.com.mx/Content/images/products/img_large/0750103131001L.jpg");
		pepsi.setDescription("Gaseosa Pepsi 1,5 Lt");
		Set<Commerce>commerces1=new HashSet<>();
		
		commerces1.add(carrefour);
		commerces1.add(coto);
		ayudin1.setCommerces(commerces1);
		pepsi.setCommerces(commerces1);

		session.save(pepsi);
		
	}

}
