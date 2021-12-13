package com.vyatsu.lab;

import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class CommandListener {
    private final Scanner _scanner;
    private final ClientService _service;

    public CommandListener(Scanner s, ClientService c) {
        _scanner = s;
        _service = c;
    }

    public void Start() {
        boolean key = true;
        while (key) {
            System.out.println("Список команд\n/showProductsByPerson имя_покупателя" +
                    "\n/findPersonsByProductTitle название_товара" +
                    "\n/removePerson имя_элемента" +
                    "\n/removeProduct имя_элемента" +
                    "\n/buy имя_покупателя название_товара");
            String[] command = _scanner.nextLine().split(" ");
            switch (command[0]) {
                case ("/showProductsByPerson"):
                    showProductsByPerson(command[1]);
                    break;
                case ("/findPersonsByProductTitle"):
                    findPersonsByProductTitle(command[1]);
                    break;
                case ("/removePerson"):
                    removePerson(command[1]);
                    break;
                case ("/removeProduct"):
                    removeProduct(command[1]);
                    break;
                case ("/buy"):
                    buy(command[1], command[2]);
                    break;
                case ("/exit"):
                    key = false;
                    break;
                default:
                    System.out.println("Введена неверная команда");
                    break;
            }
        }
    }

    private void showProductsByPerson(String client) {
        String hql = "FROM Client WHERE name =" + "'" + client + "'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Client> list = (List<Client>) session.createQuery(hql).list();
        if (list.isEmpty()) {
            closeSession(session);
            System.out.println("Данного клиента не существует\n");
            return;
        }
        List<Product> products = list.get(0).getProducts();
        closeSession(session);
        if (products.isEmpty()) {
            System.out.println("Данный клиент ничего не покупал\n");
            return;
        }
        for (Product p :
                products) {
            System.out.println(p.getName());
        }
        System.out.println();
    }

    private void findPersonsByProductTitle(String product) {
        String hql = "FROM Product WHERE title =" + "'" + product + "'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Product> list = (List<Product>) session.createQuery(hql).list();
        if (list.isEmpty()) {
            closeSession(session);
            System.out.println("Данного продукта не существует\n");
            return;
        }
        List<Client> clients = list.get(0).getClients();
        closeSession(session);
        if (clients.isEmpty()) {
            System.out.println("Данный продукта никто не покупал\n");
            return;
        }
        for (Client c :
                clients) {
            System.out.println(c.getName());
        }
        System.out.println();
    }

    private void removePerson(String client) {
        String hql = "FROM Client WHERE name =" + "'" + client + "'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Client> list = (List<Client>) session.createQuery(hql).list();
        session.close();
        if (list.isEmpty()) {
            System.out.println("Данного клиента не существует\n");
            return;
        }
        _service.deleteUser(list.get(0));
        System.out.println("Клиент удалён.\n");
    }

    private void removeProduct(String product) {
        String hql = "FROM Product WHERE title =" + "'" + product + "'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Product> list = (List<Product>) session.createQuery(hql).list();
        session.close();
        if (list.isEmpty()) {
            System.out.println("Данного продукта не существует\n");
            return;
        }
        _service.deleteProduct(list.get(0));
        System.out.println("Продукт удалён.\n");
    }

    private void buy(String client, String product) {
        // Получение Клиента
        String hql = "FROM Client WHERE name =" + "\'" + client + "\'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Client> resultClient = (List<Client>) session.createQuery(hql).list();
        if (resultClient.isEmpty()) {
            closeSession(session);
            System.out.println("Данного клиента не существует\n");
            return;
        }
        Client tmpClient = resultClient.get(0);
        closeSession(session);

        // Получение Продукта
        hql = "FROM Product WHERE title =" + "\'" + product + "\'";
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Product> resultProduct = (List<Product>) session.createQuery(hql).list();
        if (resultProduct.isEmpty()) {
            closeSession(session);
            System.out.println("Данного продукта не существует\n");
            return;
        }
        Product tmpProduct = resultProduct.get(0);
        closeSession(session);

        // Покупка
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        tmpProduct.addClient(tmpClient);
        tmpClient.addProduct(tmpProduct);
        session.update(tmpClient);
        session.update(tmpProduct);
        closeSession(session);
        System.out.println("Покупка завершена\n");
    }

    private void closeSession(Session s) {
        s.getTransaction().commit();
        s.close();
    }
}
