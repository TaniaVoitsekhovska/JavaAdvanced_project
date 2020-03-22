package ua.lviv.home.daos;

import java.util.List;

public interface CRUD<T> {

    T insert(T t);

    T read(int id);

    void update(T t,int id);

    void delete(int id);

    List<T> readAll();
}
