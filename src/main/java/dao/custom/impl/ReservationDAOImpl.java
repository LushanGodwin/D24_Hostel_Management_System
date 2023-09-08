package dao.custom.impl;

import dao.custom.ReservationDAO;
import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Reservation");
        nativeQuery.addEntity(Reservation.class);
        List<Reservation> reservations = nativeQuery.list();

        transaction.commit();
        session.close();

        return reservations;


    }

    @Override
    public boolean save(Reservation entity) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public boolean update(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC");
        query.setMaxResults(1);
        List results = query.list();

        transaction.commit();
        session.close();

        return (results.size() == 0) ? null : (String) results.get(0);

    }

    @Override
    public boolean delete(Reservation entity) {
        return false;


    }

    @Override
    public Reservation search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.get(Reservation.class, id);

        transaction.commit();
        session.close();
        return reservation;

    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = new Reservation();
        reservation.setRes_id(id);

        session.delete(reservation);


        transaction.commit();
        session.close();

        return true;
    }
}
