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
		// TODO Auto-generated method stub
		return rankingDao.getRankingByIdCommerce(id);
	}

	@Override
	public void saveRanking(Ranking ranking) {
		rankingDao.saveRanking(ranking);		
	}

	@Override
	public Long getAverageRanking(List<Ranking> rankingList) {

		
        Long average;
		Long sum=(long) 0;

		for (Ranking r :rankingList ) 
		{
		sum=sum + r.getValue();	
		}
		
		average=sum/rankingList.size();
		
		return average;
	
	}

}
