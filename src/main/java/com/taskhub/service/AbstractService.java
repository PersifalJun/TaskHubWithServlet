package com.taskhub.service;


import com.taskhub.dao.DAO;



import java.util.List;

public abstract class AbstractService<T> implements Service<T> {
    protected final DAO<T> dao;

    public AbstractService(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> getFixedCount(int offset, int limit) {
        return dao.getItems(offset, limit);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id).orElseThrow();
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    public T save(T entity) {
        return dao.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public T update(T t) {
        return dao.update(t);
    }

    @Override
    public int getAllCount() {
        return dao.getAllCount();
    }

    @Override
    public abstract List<T> getByUserId(Long userId, DAO<T> dao);




}
