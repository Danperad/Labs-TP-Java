package com.vyatsu.lab;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {
    public Client findClientByName(String name)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, name);
    }
    public Product findProductByName(String name)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Product.class, name);
    }

    public Client findClientById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public Product findProductById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Product.class, id);
    }

    public void insertClient (Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    public void insertProduct (Product product){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    public void updateClient(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(client);
        tx1.commit();
        session.close();
    }

    public void updateProduct(Product product){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(product);
        tx1.commit();
        session.close();
    }

    public void deleteClient(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    public void deleteProduct(Product product){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(product);
        tx1.commit();
        session.close();
    }

    public List<Client> findAllClients() {
        return (List<Client>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Client").list();
    }

    public List<Product> findAllProducts() {
        return (List<Product>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Product").list();
    }
}
