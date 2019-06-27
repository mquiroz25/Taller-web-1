package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.ItemCommerce;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("ItemCommerceDao")
public class ItemCommerceDaoImpl implements ItemCommerceDao {

    @Inject
    private SessionFactory sessionFactory;

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
}
