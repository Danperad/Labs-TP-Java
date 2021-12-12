package com.vyatsu.lab;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        ClientService clientService = new ClientService();
        Client[] clients = {
                new Client("Иван"),
                new Client("Петр")
        };
        Product[] products = {
                new Product("Яйца", 100),
                new Product("Колбаса", 250),
                new Product("Молоко", 50),
                new Product("Хлеб", 30)
        };
        clients[0].addProduct(products[0]);
        clients[1].addProduct(products[2]);
        products[3].addClient(clients[0]);
        for (Product p : products) {
            clientService.insertProduct(p);
        }
        for (Client c : clients) {
            clientService.insertClient(c);
        }

        Scanner scanner = new Scanner(System.in);
        CommandListener commandListener = new CommandListener(scanner, clientService);
        commandListener.Start();
    }
}
