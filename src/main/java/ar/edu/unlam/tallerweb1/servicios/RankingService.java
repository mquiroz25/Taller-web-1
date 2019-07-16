package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

public interface RankingService {
   List<Ranking>getRankingListByIdCommerce(Long id);
   void saveRanking(Ranking ranking);
   Ranking getAverageForCriteriaAndSetRankingToCommerce(Double attention,Double speed,Double prices,String review,Commerce commerce);
}
