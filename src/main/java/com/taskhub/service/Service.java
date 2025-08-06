package com.taskhub.service;




import java.util.List;

public interface Service<T> {

    List<T> getFixedCount(int offset, int limit);

     T getById(Long id);

     List<T> getAll();

     T save(T entity);

     void deleteById(Long id);

     T update(T t);

     int getAllCount();

}
