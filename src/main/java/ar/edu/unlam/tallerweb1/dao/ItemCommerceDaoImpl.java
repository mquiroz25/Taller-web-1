package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Repository("ItemCommerceDao")
public class ItemCommerceDaoImpl implements ItemCommerceDao {

    @Inject
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public  void notifyNoStock(ItemCommerce itemCommerce){
        final Session session = sessionFactory.getCurrentSession();
        session.save(itemCommerce);
    }

    @Override
    public ItemCommerce getItemCommerceById(Long idCommerce, Long idItem){
        final Session session = sessionFactory.getCurrentSession();
        Object obj = session.createCriteria(ItemCommerce.class)
                .createAlias("commerce", "c")
                .createAlias("item", "i")
                .add(Restrictions.eq("c.commerce_id", idCommerce))
                .add(Restrictions.eq("i.id", idItem))
                .uniqueResult();
        ItemCommerce item = (ItemCommerce)obj;

        return item;
    }

	@Override
	public Integer getStock(Long idCommerce, Long idItem) {
		final Session session = sessionFactory.getCurrentSession();
        Object obj = session.createCriteria(ItemCommerce.class)
                .createAlias("commerce", "c")
                .createAlias("item", "i")
                .add(Restrictions.eq("c.commerce_id", idCommerce))
                .add(Restrictions.eq("i.id", idItem))
                .uniqueResult();
        ItemCommerce item = (ItemCommerce)obj;
        Integer stock = item.getStock();
        return stock;
	}

	@Transactional
	@Override
	public void deductStock(Long idCommerce, Long idItem, Integer amount) {
    	final Session session = sessionFactory.getCurrentSession();
			Object obj = session.createCriteria(ItemCommerce.class)
	                .createAlias("commerce", "c")
	                .createAlias("item", "i")
	                .add(Restrictions.eq("c.commerce_id", idCommerce))
	                .add(Restrictions.eq("i.id", idItem))
	                .uniqueResult();
	        ItemCommerce itemCommerce = (ItemCommerce)obj;
			Integer stock = itemCommerce.getStock();
			Integer newStock = stock - amount;
			itemCommerce.setStock(newStock);
			session.update(itemCommerce);
	}
}
