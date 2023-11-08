package com.example;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class App 
{
    public static void main( String[] args )
    {

        try{
            System.out.println("server started and executing");
            ServerSocket server = new ServerSocket(3000);
            ArrayList<MyThread> threads = new ArrayList<>();

            while(true){
                Socket client = server.accept();
                MyThread clientManager = new MyThread(client, threads);
                clientManager.start();
                threads.add(clientManager);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}
