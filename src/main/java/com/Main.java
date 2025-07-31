package com;

import com.taskhub.config.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class Main {

    public static void main(String[] args) {


        SessionFactoryProvider sessionFactoryProvider = new PropertiesSessionFactoryProvider();
        sessionFactoryProvider.getSessionFactory();

        //Проверка рподключения к бд и работы  hibernate
        try (Session session = sessionFactoryProvider.getSessionFactory().openSession()) {


            Transaction tx = session.beginTransaction();

            try {
                Query query = session.createNativeQuery(
                        "INSERT INTO users(id, name, email, password) VALUES (:id, :name, :email, :password)"
                );
                query.setParameter("id", 1);
                query.setParameter("name", "admin");
                query.setParameter("email", "admin");
                query.setParameter("password", "admin");
                query.executeUpdate();

                tx.commit();
                System.out.println("Пользователь добавлен!");
            } catch (Exception ex) {
                tx.rollback();
                throw new HibernateException("Ошибка при вставке пользователя", ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
