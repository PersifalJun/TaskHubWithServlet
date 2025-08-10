package com.taskhub.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

public abstract class AbstractDAO<T> implements DAO<T> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractDAO(final Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }


    public Optional<T> getById(final Long id) {
        Optional<T> result = null;
        try {
            Session session = getCurrentSession();
            session.beginTransaction();
            result = Optional.ofNullable(getCurrentSession().find(clazz, id));
        } catch (Exception ex) {
            if (isNull(id)) {
                getCurrentSession().getTransaction().rollback();
                throw new IllegalArgumentException("id is null");
            }
        }
        return result;
    }







    public List<T> getItems(int offset, int count) {
        List<T> result;
        try{
            Session session = getCurrentSession();
            session.beginTransaction();
            result = session.createQuery("from " + clazz.getName(), clazz).setFirstResult(offset)
                    .setMaxResults(count)
                    .getResultList();
            session.getTransaction().commit();
        }catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("findAll failed", e);
        }
        return result;
    }


    public List<T> findAll() {

        List<T> result;
        try {
            Session session = getCurrentSession();
            session.beginTransaction();
            result = session.createQuery("from " + clazz.getName() + " e ORDER BY e.id ASC", clazz).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("findAll failed", e);
        }
        return result;
    }


    public T save(final T entity) {
        try {
            Session session = getCurrentSession();
            session.beginTransaction();
            if (isNull(entity)) throw new RuntimeException("Nothing to save");
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Save failed", e);
        }
    }

    public T update(final T entity) {
        try {
            Session session = getCurrentSession();
            session.beginTransaction();
            if (Objects.isNull(entity)) throw new RuntimeException("Nothing to update");
            T merged = session.merge(entity);
            session.getTransaction().commit();
            return merged;
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Update failed", e);
        }
    }



    public void delete(final T entity) {
        try {
            Session session = getCurrentSession();
            session.beginTransaction();
            if (Objects.isNull(entity)) throw new RuntimeException("Nothing to delete");
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Delete failed", e);
        }
    }


    public void deleteById(final Long entityId) {
        try {
            Session session = getCurrentSession();
            session.beginTransaction();
            if (Objects.isNull(entityId)) throw new RuntimeException("Nothing to delete");
            final T entity = session.find(clazz, entityId);
            if (Objects.nonNull(entity)) {
                session.remove(entity);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Deletion by id was failed", e);
        }
    }

    public int getAllCount(){
        return Math.toIntExact(getCurrentSession().createQuery("select count(*) from " + clazz.getName(),Long.class).uniqueResult());
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}