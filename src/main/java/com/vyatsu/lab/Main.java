package com.vyatsu.lab;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        ClientService clientService = new ClientService();
        Client[] clients = {
                new Client("Александр"),
                new Client("Иван"),
                new Client("Дмитрий"),
                new Client("Петя"),
        };
        Product[] products = {
                new Product("Хлеб", 100),
                new Product("Творог", 250),
                new Product("Соль", 50),
                new Product("Сахар", 30)
        };
        clients[0].addProduct(products[0]);
        clients[2].addProduct(products[3]);
        products[2].addClient(clients[1]);
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
