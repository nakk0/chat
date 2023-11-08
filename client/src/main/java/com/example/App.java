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

            String answer = "";

            //Thread1 that gets created to read line and then killed
            //Thread2 that does the rest of the stuff

            socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
