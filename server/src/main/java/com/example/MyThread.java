package com.example;

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class MyThread extends Thread {
    Socket client;
    ArrayList<MyThread> threads = new ArrayList<>();

    public MyThread(Socket socket, ArrayList<MyThread> threads) {
        this.client = socket;
        this.threads = threads;
    }

    public void run(){

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());   

            System.out.println("client connected");

            String clientName = in.readLine();
            System.out.println("Ricevuto nome: " + clientName);

            String choice = "";
            do{
                choice = in.readLine();

                if(choice.equals("0")){
                    String message = in.readLine();
                    for(MyThread thread : threads){
                        thread.sendMessage(message, out);
                    }
                }
                //TODO other if in the case of having to send message to a single 
            }while(!choice.equals("x"));
            


            client.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }

    public void sendMessage(String m, DataOutputStream out) {
        try {
            out.writeBytes(m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
