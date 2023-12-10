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

            String clientName = "";

            do{
                if(clientName.equals("0"))
                    System.out.println("nome non consentito");
                System.out.println("Inserire nome:");
                clientName = scanner.nextLine();
            }while(clientName.equals("0"));
            
            out.writeBytes(clientName + "\n");

            MessageReceiver messageReceiver = new MessageReceiver(in);
            messageReceiver.start();

            MessageSender messageSender = new MessageSender(in, out);
            messageSender.start();

            messageSender.join();
            messageReceiver.join();

            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
