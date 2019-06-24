package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Ranking;

public interface RankingService {

	   List<Ranking>getRankingByIdCommerce(Long id);
	   void saveRanking(Ranking ranking);
	//   	Double getAverageRanking(List<Ranking> rankingList);
	   	Double getAverageForCriteria(Double attention,Double speed,Double prices);
}
