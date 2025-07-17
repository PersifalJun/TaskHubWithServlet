package com;

import com.taskhub.config.*;


public class Main {
    public static void main(String[] args) {

        SessionFactoryProvider sessionFactoryProvider = new PropertiesSessionFactoryProvider();
        sessionFactoryProvider.getSessionFactory();

    }
}
