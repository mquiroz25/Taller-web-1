package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

@Repository("ReserveDao")
public class ReserveDaoImpl implements ReserveDao {
    @Inject
    private SessionFactory sessionFactory;

	@Override
	public void saveReserve(Reserve reserve) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(reserve);
	}

    
}
