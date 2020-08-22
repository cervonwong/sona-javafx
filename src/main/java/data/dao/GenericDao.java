package main.java.data.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    // CRUD METHODS

    List<T> getAll();

    Optional<T> get(int id);

    void create(T t);

    void update(T t);

    void delete(T t);
}
