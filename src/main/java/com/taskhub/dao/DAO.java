package com.taskhub.dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
     Optional<T> getById(final Long id);

     List<T> getItems(int offset, int count);

     List<T> findAll();

     T save(final T entity);

     T update(final T entity);

     void delete(final T entity);

     void deleteById(final Long entityId);

     int getAllCount();

     Session getCurrentSession();
}
