package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.CommerceDao;
import ar.edu.unlam.tallerweb1.modelo.Commerce;
import ar.edu.unlam.tallerweb1.modelo.Ranking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("CommerceService")
@Transactional
public class CommerceServiceImpl implements CommerceService {
    @Inject
    private CommerceDao commerceDao;


    public List<Commerce> getCommercesByDistance(Long distance, Double userLat, Double userLon){
        List<Commerce> commerceList = commerceDao.getCommerces();
        List<Commerce> commerceToRemove = new ArrayList<>();

        for (Commerce commerce: commerceList) {
            Long distanceToCommerce = calculateDistanceBetweenCommerceAndUser(userLat, userLon, commerce.getLatitude(), commerce.getLongitude());
            if (distance < distanceToCommerce) {
                commerceToRemove.add(commerce);
            } else {
            	commerce.setDistance(distanceToCommerce);
            }
        }

        for (Commerce commerce: commerceToRemove){
            commerceList.remove(commerce);
        }

        return commerceList;
    }

    private Long calculateDistanceBetweenCommerceAndUser(Double LatUser, Double LonUser, Double LatCommerce, Double LonCommerce){
        return calculateDistance(LatUser, LonUser, LatCommerce, LonCommerce);
    }

    private Long calculateDistance(Double Lat1, Double Lon1, Double Lat2, Double Lon2){
        return Math.round((Math.acos(((Math.sin(Math.toRadians(Lat2)))*(Math.sin(Math.toRadians(Lat1)))) + ((Math.cos(Math.toRadians(Lat2)))*(Math.cos(Math.toRadians(Lat1)))*(Math.cos(Math.toRadians(Lon1 - Lon2))))) * 6371));
    }

	@Override
	public Commerce getCommerceById(Long id) {
		return commerceDao.getCommerceById(id);
	}

	@Override
	public void calculateAverageRankingListAndSetToCommerce(Commerce commerce, List<Ranking> rankingList) {
		commerceDao.calculateAverageRankingListAndSetToCommerce(commerce, rankingList);
		
	}
}
