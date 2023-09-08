package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Room> roomList = session.createNativeQuery("SELECT * FROM Room").addEntity(Room.class).list();

        transaction.commit();
        session.close();
        return roomList;
    }

    @Override
    public boolean update(Room room) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(room);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() {
        return null;
    }

    @Override
    public Room search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM room WHERE room_type_id = :room_type_id");
        nativeQuery.setParameter("room_type_id",id);

        nativeQuery.addEntity(Room.class);
        Room room = (Room) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return room;
    }
}
