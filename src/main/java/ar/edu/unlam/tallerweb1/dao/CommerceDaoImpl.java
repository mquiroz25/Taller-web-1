package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import java.util.List;

@Repository("CommerceDao")
public class CommerceDaoImpl implements CommerceDao {
    @Inject
    private SessionFactory sessionFactory;

    public List<Commerce> getCommerces(){
        final Session session = sessionFactory.getCurrentSession();

        @SuppressWarnings("unchecked")
        List<Commerce> commerceList = session.createCriteria(Commerce.class)
                .list();

        return commerceList;
    }

	public Commerce getCommerceById(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		Commerce commerce = (Commerce) session.createCriteria(Commerce.class)
				.add(Restrictions.eq("commerce_id", id))
				.uniqueResult();
		return commerce;
	}
}
