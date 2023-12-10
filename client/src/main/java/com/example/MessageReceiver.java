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
                String sender = in.readLine();
                String isPrivate = in.readLine();
                String message = in.readLine();
                if(sender.equals("0")){
                    sender = "[System]";
                    if(message.equals("0"))
                        message = "il client inserito non è connesso, premere invio e ritentare";
                }

                if (isPrivate.equals("n")) {
                    System.out.println(sender + ": " + message);
                } else if (isPrivate.equals("y")) {
                    System.out.println(sender + "(private): " + message);
                } else {
                    System.out.println("Internal error");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
