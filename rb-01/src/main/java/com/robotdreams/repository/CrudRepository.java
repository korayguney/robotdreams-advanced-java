package com.robotdreams.repository;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    T findById(long id);
    void saveToDatabase(T object);
    void deleteFromDatabase(T object);
    void deleteFromDatabase(int id);
    void updateOnDatabase(T object);

}
