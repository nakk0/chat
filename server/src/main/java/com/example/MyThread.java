package com.example;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class MyThread extends Thread{
    Socket client;

    public MyThread(Socket socket, ArrayList<MyThread> threads) {
        this.client = socket;
    }

    public void run(){

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());   

            out.writeBytes("Inserisci il tuo nome: ");
            String name = in.readLine();
            
            System.out.println("client connected");


            client.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}
