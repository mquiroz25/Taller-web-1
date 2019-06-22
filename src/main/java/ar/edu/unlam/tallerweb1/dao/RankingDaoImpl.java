package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Ranking;

@Repository("RankingDao")
public class RankingDaoImpl implements RankingDao {
	
	  @Inject
	    private SessionFactory sessionFactory;

	@Override
	public List<Ranking> getRankingByIdCommerce(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        
        List<Ranking>list=session.createCriteria(Ranking.class)
        		.createAlias("commerce", "c")
        		.add(Restrictions.eq("c.commerce_id",id))
        		.list();
		return list;
	}

	@Override
	public void saveRanking(Ranking ranking) {
	final Session session = sessionFactory.getCurrentSession();
		
		session.save(ranking);
	}

}
