package com.taskhub.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractDAO<T> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractDAO(final Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }


    public Optional<T> getById(final Long id) {
        if  (Objects.isNull(id)) {
            throw new IllegalArgumentException("id is null");
        }
        return Optional.ofNullable(getCurrentSession().find(clazz, id));
    }


    public List<T> getItems(int offset, int count) {
        return getCurrentSession()
                .createQuery("from " + clazz.getName(), clazz)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }


    public List<T> findAll() {
        return getCurrentSession()
                .createQuery("from " + clazz.getName(), clazz)
                .list();
    }


    public T save(final T entity) {
        try{
            getCurrentSession().getTransaction().begin();
            if(Objects.nonNull(entity)) {
                getCurrentSession().persist(entity);
            }
            else{
                throw new RuntimeException("Nothing to save");
            }
        }
        catch(HibernateException hex){
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Save failed");
        }
        finally{
            getCurrentSession().getTransaction().commit();
            getCurrentSession().close();
        }
        return entity;
    }


    public T update(final T entity) {
        try{
            getCurrentSession().getTransaction().begin();
            if(Objects.nonNull(entity)) {
                getCurrentSession().merge(entity);
            }
            else{
                throw new RuntimeException("Nothing to update");
            }
        }
        catch(HibernateException hex){
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Update failed");
        }
        finally{
            getCurrentSession().getTransaction().commit();
            getCurrentSession().close();
        }
        return entity;
    }


    public void delete(final T entity) {
        try{
            getCurrentSession().getTransaction().begin();
            if(Objects.nonNull(entity)) {
                getCurrentSession().remove(entity);
            }
            else{
                throw new RuntimeException("Nothing to update");
            }
        }
        catch(HibernateException hex){
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Delete failed");
        }
        finally{
            getCurrentSession().getTransaction().commit();
            getCurrentSession().close();
        }
    }


    public void deleteById(final Long entityId) {
        try{
            getCurrentSession().getTransaction().begin();
            if(Objects.nonNull(entityId)) {
                final T entity = getById(entityId).orElseThrow();
                delete(entity);
            }
            else{
                throw new RuntimeException("Nothing to delete");
            }

        }
        catch(HibernateException hex){
            getCurrentSession().getTransaction().rollback();
            throw new HibernateException("Deletion by id was failed");
        }
        finally{
            getCurrentSession().getTransaction().commit();
            getCurrentSession().close();
        }
    }

    public int getAllCount(){
        return Math.toIntExact(getCurrentSession().createQuery("select count(*) from " + clazz.getName(),Long.class).uniqueResult());
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}