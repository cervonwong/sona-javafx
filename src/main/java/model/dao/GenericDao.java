package main.java.model.dao;

import java.util.List;

public interface GenericDao<T> {

    // CRUD METHODS

    List<T> getAll();

    T get(int id);

    void create(T t);

    void update(T t);

    void delete(T t);
}
