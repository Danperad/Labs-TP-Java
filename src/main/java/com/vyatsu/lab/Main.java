package com.vyatsu.lab;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class Main {
    private static int optimSum;
    private static int pissSum;
    private static long optimTime;
    private static long pisimTime;
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        /*recreateTable(factory);
        long start = System.currentTimeMillis();
        optimistic(factory);
        optimTime = System.currentTimeMillis() - start;*/
        recreateTable(factory);
        long start = System.currentTimeMillis();
        pisimistic(factory);
        pisimTime = System.currentTimeMillis() - start;
        // System.out.println("Оптимистик. (ms): " + optimTime + " sum: " + optimSum);
        // Оптимистик. (ms): 169620 sum: 80000
        System.out.println("Писимистик. (ms): " + pisimTime + " sum: " + pissSum);
        // Писимистик. (ms): 164654 sum: 80000
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Item.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    private static int getSum() {
        int sum = 0;
        for (int i = 1; i < 41; i++) {
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            Item it = session.get(Item.class, i);
            sum += it.getValue();
            session.getTransaction().commit();
            session.close();
        }
        return sum;
    }

    private static void recreateTable(SessionFactory factory) {
        Item item = new Item(0);
        for (int i = 0; i < 40; i++) {
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
        }
    }

    private static void optimistic(SessionFactory factory) {
        CyclicBarrier cb = new CyclicBarrier(4);
        Random rnd = new Random();
        Thread[] thread = new Thread[4];
        for (int i = 0; i < 4; i++) {
            thread[i] = new Thread(() -> {
                for (int j = 0; j < 20000; j++) {
                    boolean check = false;
                    while (!check) {
                        Session session = factory.getCurrentSession();
                        try {
                            session.beginTransaction();
                            Item it = session.get(Item.class, rnd.nextInt(1, 41));
                            it.incValue();
                            Thread.sleep(5);
                            session.save(it);
                            session.getTransaction().commit();
                            session.close();
                            check = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (HibernateException | OptimisticLockException e) {
                            session.getTransaction().rollback();
                        }
                    }
                }
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 4; i++)
            thread[i].start();
        try {
            for (int i = 0; i < 4; i++) {
                thread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        optimSum = getSum();
    }

    private static void pisimistic(SessionFactory factory) {
        CyclicBarrier cb = new CyclicBarrier(4);
        Random rnd = new Random();
        Thread[] thread = new Thread[4];
        for (int i = 0; i < 4; i++) {
            thread[i] = new Thread(() -> {
                for (int j = 0; j < 20000; j++) {
                    boolean check = false;
                    while (!check) {
                        Session session = factory.getCurrentSession();
                        try {
                            session.beginTransaction();
                            Item it = session.createQuery("FROM Item WHERE id = " + rnd.nextInt(1, 41), Item.class)
                                    .setLockMode(LockModeType.PESSIMISTIC_WRITE).getResultList().get(0);
                            it.incValue();
                            Thread.sleep(5);
                            session.save(it);
                            session.getTransaction().commit();

                            session.close();
                            check = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (HibernateException | OptimisticLockException e) {
                            session.getTransaction().rollback();
                        }
                    }
                }
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 4; i++)
            thread[i].start();
        try {
            for (int i = 0; i < 4; i++) {
                thread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pissSum = getSum();
    }
}
