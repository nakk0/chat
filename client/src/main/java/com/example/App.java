package com.example;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        try{
            Socket socket = new Socket("localhost", 3000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner s = new Scanner(System.in);

            MessageReceiver mr = new MessageReceiver(in);
            in.readLine();

            Message message = new Message(s, out);

            System.out.println("Inserire nome");
            out.writeBytes(s.nextLine());

            System.out.println("scrivere 0 per mandare messaggio a tutti, o 1 per lista di client connessi");
            message.start();
            
            //now the client sends the actual message but I think 
            //it should receive something from the server, either "send the message" if 0 or a list of threads if 1
            message.start();

            socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
