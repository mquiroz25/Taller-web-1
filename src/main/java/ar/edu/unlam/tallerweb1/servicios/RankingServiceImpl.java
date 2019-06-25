package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.RankingDao;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

@Service("RankingService")
@Transactional
public class RankingServiceImpl implements RankingService {
	@Inject
	private RankingDao rankingDao;

	@Override
	public List<Ranking> getRankingByIdCommerce(Long id) {
		return rankingDao.getRankingByIdCommerce(id);
	}

	@Override
	public void saveRanking(Ranking ranking) {
		rankingDao.saveRanking(ranking);		
	}

	/*@Override
	public Double getAverageRanking(List<Ranking> rankingList) {

		
		Double average;
		Double sum= 0.0;

		for (Ranking r :rankingList ) 
		{
		sum=sum + r.getValue();	
		}
		
		average=sum/rankingList.size();
		
		 average = Math.round(average * 10.0) / 10.0;
		 
		return average;
	
	}*/

	@Override
	public Double getAverageForCriteria(Double attention, Double speed, Double prices) {
	 	Double av = (attention+ speed + prices)/3;
		double avOneDecimal = Math.round(av * 10.0) / 10.0;
	    	
		return avOneDecimal;
	}
}
