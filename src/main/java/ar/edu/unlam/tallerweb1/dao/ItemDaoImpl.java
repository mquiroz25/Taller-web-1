package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Item;
import ar.edu.unlam.tallerweb1.modelo.Message;
import com.mysql.jdbc.PreparedStatement;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    public List<Item> obtainItemsByCategory(Message message){
        final Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Item> items = session.createCriteria(Item.class)
                .createAlias("category", "c")
                .add(Restrictions.ilike("c.name", message.getCategory(), MatchMode.ANYWHERE))
                .list();
        return items;
    }
    // sql native porque no sé si sea posible hacerlo con criteria, yo no pude y estuve como 3 horas (?
    // así que GG. Si a alguno se le ocurre como implementarlo bien, sino no importa.
    // SELECT commerce.name, i.brand, category.name, ( 6371 * acos( cos( radians(-34.60362) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(-58.684985) ) + sin( radians(-34.60362) ) * sin( radians( latitude ) ) ) ) AS distance FROM commerce INNER JOIN commerce_item as ci ON commerce.id = ci.commerce_id INNER JOIN item as i ON ci.item_id = i.id INNER JOIN category ON i.category_id = category.id WHERE category.name LIKE "%galletita%" HAVING distance < 20

    @Override
    public List<Object[]> obtainItemsByCategoryAndLocation(Message message){
        final Session session = sessionFactory.getCurrentSession();
        final String sql = "SELECT commerce.name as commerce, i.brand as itemBrand, category.name as category, ( 6371 * acos( cos( radians("+message.getLatitude()+") * cos( radians( latitude ) ) *" +
                "cos( radians( longitude ) - radians("+message.getLongitude()+") ) + sin( radians("+message.getLatitude()+") ) * sin( radians( latitude ) ) ) ) ) AS distance " +
                "FROM commerce INNER JOIN commerce_item as ci ON commerce.id = ci.commerce_id INNER JOIN item as i ON ci.item_id = i.id INNER JOIN category ON i.category_id = category.id "+
                "WHERE category.name LIKE  '%"+message.getCategory()+"%' " +
                "HAVING distance < 10";
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> rows = query.list();
        return rows;

    }
}
