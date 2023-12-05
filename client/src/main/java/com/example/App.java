package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

            System.out.println("Inserire nome:");
            String clientName = scanner.nextLine();
            out.writeBytes(clientName + "\n");

            MessageReceiver messageReceiver = new MessageReceiver(in);
            messageReceiver.start();

            MessageSender messageSender = new MessageSender(out);
            messageSender.start();

            messageSender.join();
            messageReceiver.join();

            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
