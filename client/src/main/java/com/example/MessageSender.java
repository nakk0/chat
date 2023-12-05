package com.example;

import java.io.DataOutputStream;
import java.util.Scanner;

public class MessageSender extends Thread {
    private DataOutputStream out;
    private volatile boolean running = true;

    public MessageSender(DataOutputStream out) {
        this.out = out;
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);

            while (running) {
                System.out.println("Scrivere 0 per inviare un messaggio a tutti, 1 per inviare a un singolo, 2 per uscire:");
                String choice = scanner.nextLine();

                if (choice.equals("0")) {
                    System.out.println("Inserire messaggio:");
                    String message = scanner.nextLine();
                    out.writeBytes("0\n");
                    out.writeBytes(message + "\n");
                } else if (choice.equals("1")) {
                    System.out.println("Inserire nome destinatario:");
                    String recipient = scanner.nextLine();
                    System.out.println("Inserire messaggio:");
                    String message = scanner.nextLine();
                    out.writeBytes("1\n");
                    out.writeBytes(recipient + "\n");
                    out.writeBytes(message + "\n");
                } else if (choice.equals("2")) {
                    out.writeBytes("2\n");
                    running = false;
                    out.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
