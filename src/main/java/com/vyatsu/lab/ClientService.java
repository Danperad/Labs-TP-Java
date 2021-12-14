package com.vyatsu.lab;


import java.util.List;

public class ClientService {
    private ClientDao clientsDao = new ClientDao();

    public ClientService() {
    }

    public Client findClientByID(int id) {
        return clientsDao.findClientById(id);
    }

    public Product findProductByID(int id) {
        return clientsDao.findProductById(id);
    }

    public void insertClient(Client client) {
        clientsDao.insertClient(client);
    }

    public void insertProduct(Product product) {
        clientsDao.insertProduct(product);
    }

    public void deleteUser(Client client) {
        clientsDao.deleteClient(client);
    }

    public void deleteProduct(Product product) {
        clientsDao.deleteProduct(product);
    }

    public void updateUser(Client client) {
        clientsDao.updateClient(client);
    }

    public void updateProduct(Product product) {
        clientsDao.updateProduct(product);
    }

    public List<Client> findAllClients() {
        return clientsDao.findAllClients();
    }

    public List<Product> findAllProducts() {
        return clientsDao.findAllProducts();
    }

}
