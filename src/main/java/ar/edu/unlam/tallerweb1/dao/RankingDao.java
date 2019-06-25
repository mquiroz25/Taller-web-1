package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

public interface RankingDao {
	List<Ranking>getRankingByIdCommerce(Long id);
	void saveRanking (Ranking ranking);
}
