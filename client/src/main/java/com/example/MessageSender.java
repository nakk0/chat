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
                        "Scrivere: \n0 per inviare un messaggio a tutti \n1 per inviare a un singolo \n2 per una lista dei client connessi \nx per uscire:");
                String choice = scanner.nextLine();

                switch(choice){
                    case "0":{
                        System.out.println("Inserire messaggio:");
                        String message = scanner.nextLine();
                        out.writeBytes("0\n");
                        out.writeBytes(message + "\n");
                        break;
                    }
                    case "1":{
                        out.writeBytes("1\n");

                        System.out.println("Inserire nome destinatario:");
                        String recipient = scanner.nextLine();
                        out.writeBytes(recipient +"\n");

                        System.out.println("Inserire messaggio:");
                        String message = scanner.nextLine();
                        out.writeBytes(message + "\n");
                        break;
                    }
                    case "2":
                        out.writeBytes("2\n");
                        break;

                    case "x":
                        out.writeBytes("x\n");
                        running = false;
                        out.close();
                    break;

                    case "":
                        break;
                        
                    default:
                        System.out.println("input \"" + choice + "\" non è corretto");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
