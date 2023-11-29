package com.example;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class Message extends Thread{
    private Scanner in;
    private DataOutputStream out;

    public Message(Scanner in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void run(){
        try{
            String input = in.nextLine();
            out.writeBytes(input);
            Thread.currentThread().interrupt();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
