package com.example;

import java.io.BufferedReader;

public class MessageReceiver extends Thread {
    private BufferedReader in;

    public MessageReceiver(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        try {
            while (true) {
                String s = in.readLine();
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
