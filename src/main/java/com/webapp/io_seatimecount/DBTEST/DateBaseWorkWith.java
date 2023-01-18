package com.webapp.io_seatimecount.DBTEST;

import com.webapp.io_seatimecount.hybernate.entity.TimeToAdd;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DateBaseWorkWith {

    public static void pushToDB(TimeToAdd inputFromWS) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // <- to be in resources folder!
                .addAnnotatedClass(TimeToAdd.class) // <- class in which all columns and info is determined
                .buildSessionFactory(); // <- creating the factory
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(inputFromWS); //<- instead of save which is deprecated
            session.getTransaction().commit(); // <- to commit the change (can be use roll back)
        } finally {
            factory.close();
        }

    }


}

