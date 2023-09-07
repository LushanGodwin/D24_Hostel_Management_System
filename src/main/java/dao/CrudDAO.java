package dao;

import entity.Student;
import entity.User;

import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
    boolean save(T entity);

    List<T> getAll();

    boolean update(T student);

    boolean delete(T entity);

    String generateNextId();

    T search(String id);
}
