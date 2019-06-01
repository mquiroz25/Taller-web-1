package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository("ItemDao")
public class ItemDaoImpl implements ItemDao {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<Item> obtainItemsByCategoryAndLocation(Message message){
        final Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Item> items = session.createCriteria(Item.class)
                .createAlias("category", "c")
                .add(Restrictions.ilike("c.name", message.getCategory(), MatchMode.ANYWHERE))
                .list();
        return items;
    }
}
