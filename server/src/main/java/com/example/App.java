package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Server started and waiting for connections...");
            ServerSocket server = new ServerSocket(3000);
            ArrayList<MyThread> threads = new ArrayList<>();

            while (true) {
                Socket client = server.accept();
                MyThread clientManager = new MyThread(client, threads);
                clientManager.start();
                threads.add(clientManager);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
