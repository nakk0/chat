package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MyThread extends Thread {
    private Socket client;
    private ArrayList<MyThread> threads;
    private DataOutputStream out;
    private String clientName;

    public MyThread(Socket socket, ArrayList<MyThread> threads) {
        this.client = socket;
        this.threads = threads;
    }

    public void run() {
        try {
            BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
            out = new DataOutputStream(client.getOutputStream());

            System.out.println("Client connected");

            clientName = in.readLine();
            System.out.println("Received name: " + clientName);

            String choice;
            do {
                choice = in.readLine();

                if (choice.equals("0")) {
                    String message = in.readLine();
                    broadcastMessage(clientName, message);
                } else if (choice.equals("1")) {
                    String recipient = in.readLine();
                    String message;

                    if(recipientExists(recipient)){
                        message = in.readLine();
                        sendPrivateMessage(clientName, recipient, message);
                    }else{
                        sendPrivateMessage("0", clientName, "0");
                    }
                    
                } else if (choice.equals("2")){
                    sendClientList();
                }
            } while (!choice.equals("x"));

            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String sender, String isPrivate, String message) {
        try {
            out.writeBytes(sender + "\n");
            out.writeBytes(isPrivate + "\n");
            out.writeBytes(message + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void broadcastMessage(String sender, String message) {
        for (MyThread thread : threads) {
            if (thread != this) {
                thread.sendMessage(sender, "n", message);
            }
        }
    }

    private void sendPrivateMessage(String sender, String recipient, String message) {
        if (recipientExists(recipient)) {
            for (MyThread thread : threads) {
                if (thread.getClientName().equals(recipient)) {
                    thread.sendMessage(sender, "y", message);
                }
            }
        } 
    }

    private boolean recipientExists(String recipient) {
        boolean exists = false;
        for (MyThread thread : threads) {
            if (thread.getClientName().equals(recipient)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private void sendClientList(){
        String connectedClients = "";

        int i = 0;
        for(MyThread thread : threads){
            connectedClients += i++ + " - " + 
                            (thread.getClientName().equals(this.clientName) ? this.clientName + "(you)" : thread.getClientName())
                            + (i < threads.size() ? "\n" : "");
        }
        sendPrivateMessage("0", clientName, "1");
        try{
            out.writeBytes(connectedClients + "\n");
            out.writeBytes("end\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public String getClientName() {
        return clientName;
    }
}
