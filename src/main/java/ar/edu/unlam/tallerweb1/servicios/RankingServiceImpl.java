package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.RankingDao;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

@Service("RankingService")
@Transactional
public class RankingServiceImpl implements RankingService {
	@Inject
	private RankingDao rankingDao;

	@Override
	public List<Ranking> getRankingListByIdCommerce(Long id) {
		return rankingDao.getRankingByIdCommerce(id);
	}

	@Override
	public void saveRanking(Ranking ranking) {
		rankingDao.saveRanking(ranking);		
	}

	@Override
	public Ranking getAverageForCriteriaAndSetRankingToCommerce(Double attention, Double speed, Double prices,String review,Commerce commerce) {
	 	Double av = (attention+ speed + prices)/3;
		double avOneDecimal = Math.round(av * 10.0) / 10.0;

        Ranking ranking = new Ranking();
        ranking.setValue(avOneDecimal);
        ranking.setReview(review);
        ranking.setCommerce(commerce);
	    	
		return ranking;
	}
}
