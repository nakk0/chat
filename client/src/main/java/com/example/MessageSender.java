package com.example;

import java.io.*;
import java.util.Scanner;

public class MessageSender extends Thread {
    private BufferedReader in;
    private DataOutputStream out;
    private volatile boolean running = true;

    public MessageSender(BufferedReader in, DataOutputStream out) {
        this.out = out;
        this.in = in;
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);

            while (running) {
                System.out.println(
                        "Scrivere 0 per inviare un messaggio a tutti, 1 per inviare a un singolo, 2 per uscire:");
                String choice = scanner.nextLine();

                if (choice.equals("0")) {
                    System.out.println("Inserire messaggio:");
                    String message = scanner.nextLine();
                    out.writeBytes("0\n");
                    out.writeBytes(message + "\n");
                } else if (choice.equals("1")) {
                    out.writeBytes("1\n");

                    System.out.println("Inserire nome destinatario:");
                    String recipient = scanner.nextLine();
                    out.writeBytes(recipient +"\n");

                    System.out.println("Inserire messaggio(se il destinatario non esiste, cliccare invio e riprovare):");
                    String message = scanner.nextLine();
                    out.writeBytes(message + "\n");

                } else if (choice.equals("2")) {
                    out.writeBytes("2\n");
                    running = false;
                    out.close();
                } else {
                    System.out.println("input \"" + choice + "\" non è corretto");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
