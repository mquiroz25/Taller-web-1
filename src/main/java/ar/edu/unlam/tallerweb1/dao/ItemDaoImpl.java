package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Repository("ItemDao")
public class ItemDaoImpl implements ItemDao {
	
	@Inject 
	CommerceDao commerceDao;
	
    @Inject
    private SessionFactory sessionFactory;

    public List<ItemCommerce> getItemCommerceByCategoryOrBrand(String category, List<Commerce> commercesToKeep){
        final Session session = sessionFactory.getCurrentSession();

        @SuppressWarnings("unchecked")
		List<ItemCommerce> itemList = session.createCriteria(ItemCommerceImpl.class)
				.createAlias("item", "i")
        		.createAlias("i.category", "c")
				.add(Restrictions.in("commerce", commercesToKeep))
				.add(Restrictions.disjunction()
						.add(Restrictions.ilike("c.name", category, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("i.brand", category, MatchMode.ANYWHERE))
				)
				.list();

        return itemList;
    }

	@Override
	public Item getItemById(Long id){
		final Session session = sessionFactory.getCurrentSession();
		Object obj = session.createCriteria(Item.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		Item item = (Item)obj;

		return item;
	}

	@Override
	public void createItems() {
        final Session session = sessionFactory.getCurrentSession();

        // creo los comercios
		Commerce walmart = new Commerce("walmart", -34.652984000000004, -58.680162100000004);
		Commerce jumbo = new Commerce("jumbo", -34.696100213550224, -58.562610974620014);
		Commerce coto = new Commerce("coto", -34.694094417308314, -58.57249123051896);
		Commerce carrefour = new Commerce("carrefour", -34.709537794286426, -58.54431420444564);
		Commerce walmartLujan = new Commerce("Walmart Lujan", -34.5472039, -59.1134218);
		
		session.save(walmart);
		session.save(jumbo);
		session.save(coto);
		session.save(carrefour);
		session.save(walmartLujan);
		
		//creo las puntuaciones
		Ranking r1 = new Ranking();
		r1.setValue(2.0);
		r1.setCommerce(walmart);
		r1.setReview("bien");

		List<Ranking>l1 = new ArrayList<>();
		l1.add(r1);
		commerceDao.calculateAverageRankingListAndSetToCommerce(walmart, l1);

		//--------------------------------//
		
		Ranking r2 = new Ranking();
		r2.setValue(4.6);
		r2.setCommerce(jumbo);
		r2.setReview("muy buenas marcas");
		
		List<Ranking>l2 = new ArrayList<>();
		l2.add(r2);
		commerceDao.calculateAverageRankingListAndSetToCommerce(jumbo, l2);
		//--------------------------------//
		Ranking r3 = new Ranking();
		r3.setValue(3.5);
		r3.setCommerce(coto);
		r3.setReview("precios competitivos");
		
		List<Ranking>l3 = new ArrayList<>();
		l3.add(r3);
		commerceDao.calculateAverageRankingListAndSetToCommerce(coto, l3);

		//--------------------------------//
		Ranking r4 = new Ranking();
		r4.setValue(1.0);
		r4.setCommerce(carrefour);
		r4.setReview("productos vencidos");
		
		List<Ranking>l4 = new ArrayList<>();
		l4.add(r4);
		commerceDao.calculateAverageRankingListAndSetToCommerce(carrefour, l4);
	
		//--------------------------------//
		Ranking r5 = new Ranking();
		r5.setValue(2.5);
		r5.setCommerce(walmartLujan);
		r5.setReview("falta mejorar la atencion");
		
		List<Ranking>l5 = new ArrayList<>();
		l5.add(r5);
		commerceDao.calculateAverageRankingListAndSetToCommerce(walmartLujan, l5);
		
		session.save(r1);
		session.save(r2);
		session.save(r3);
		session.save(r4);
		session.save(r5);

		// creo las categorias
		Category lavandinas = new Category("lavandinas");
		Category gaseosas = new Category("gaseosas");

		session.save(lavandinas);
		session.save(gaseosas);

		// creo los productos
		Item ayudin = new Item(
				"https://perfumeriaspigmento.com.ar/media/catalog/product/cache/image/620x678/e9c3970ab036de70892d86c6d221abfe/7/9/79879.jpg",
				"AYUDIN LAVANDINA ANDINA MULTISUP ORIGIN 2 Litros",
				"Ayudin",
				lavandinas);

		Item ayudin1 = new Item(
				"http://www.servibol.com.ar/image/stores/33/cache/data/lavandina-ayudin-doble-rendimiento-bot-4l-cleansytec-23184-MLA20243633005_022015-O-500x500.jpg",
				"AYUDIN triple accion 1 litro",
				"Ayudin",
				lavandinas);
		Item pepsi = new Item(
				"https://www.superama.com.mx/Content/images/products/img_large/0750103131001L.jpg",
				"Pepsi 1.5 lts",
				"Pepsi",
				gaseosas);

		session.save(ayudin);
		session.save(ayudin1);
		session.save(pepsi);

        // configuro que comercio vende que producto a que precio y que cantidad tiene
		// coto solo tiene lavandinas
		ItemCommerce itemCommerce = new ItemCommerceImpl();

		itemCommerce.setCommerce(coto);
		itemCommerce.setStock(10);
		itemCommerce.setItem(ayudin);
		itemCommerce.setPrice(50.45);
		session.save(itemCommerce);

		itemCommerce = new ItemCommerceImpl();
		itemCommerce.setCommerce(coto);
		itemCommerce.setStock(10);
		itemCommerce.setItem(ayudin1);
		itemCommerce.setPrice(76.0);
		session.save(itemCommerce);

		// walmart vende ayudin1 y pepsi
		itemCommerce = new ItemCommerceImpl();
		itemCommerce.setCommerce(walmart);
		itemCommerce.setStock(10);
		itemCommerce.setItem(ayudin1);
		itemCommerce.setPrice(40.0);
		session.save(itemCommerce);

		itemCommerce = new ItemCommerceImpl();
		itemCommerce.setCommerce(walmart);
		itemCommerce.setStock(12);
		itemCommerce.setItem(pepsi);
		itemCommerce.setPrice(80.0);
		session.save(itemCommerce);

		// walmart Lujan tiene solo pepsi
		itemCommerce = new ItemCommerceImpl();
		itemCommerce.setCommerce(walmartLujan);
		itemCommerce.setStock(2);
		itemCommerce.setItem(pepsi);
		itemCommerce.setPrice(20.0);
		session.save(itemCommerce);

		// carrefour vende pepsi
		itemCommerce = new ItemCommerceImpl();
		itemCommerce.setCommerce(carrefour);
		itemCommerce.setStock(3);
		itemCommerce.setItem(pepsi);
		itemCommerce.setPrice(35.0);
		session.save(itemCommerce);
	}
}
