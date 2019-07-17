package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

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

	@Override
	public void calculateAverageRankingListAndSetToCommerce(Commerce commerce,List<Ranking> rankingList) {
		Double sum = 0.0;
		Double averageRanking;
		Double averageRankingRound;

		for (Ranking r : rankingList)
		{
		    sum = sum + r.getValue();
		}
		
		averageRanking = sum/rankingList.size();
		
		averageRankingRound = Math.round(averageRanking * 10.0) / 10.0;
		
		
		commerce.setAverageRanking(averageRankingRound);
	
	}
}
