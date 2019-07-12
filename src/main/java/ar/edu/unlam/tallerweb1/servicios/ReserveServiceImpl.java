package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ReserveDao;
import ar.edu.unlam.tallerweb1.modelo.Reserve;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

@Service("ReserveService")
@Transactional
public class ReserveServiceImpl implements ReserveService{

	@Inject
	private ReserveDao reserveDao;

	@Override
	public void saveReserve(Reserve reserve) {
		reserveDao.saveReserve(reserve);
	}

	
}
