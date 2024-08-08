package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee n = new Employee();
         n.setId(1);
         n.setName("Tejas");
        n.setId(2);
        n.setName("vinay");
        session.save(n);
        transaction.commit();

        System.out.println(n);

        sessionFactory.close();
        session.close();
    }
}