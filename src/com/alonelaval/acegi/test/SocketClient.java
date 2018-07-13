package com.alonelaval.acegi.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.test.SocketClient.java
 * @createDate 2009-6-12 下午02:08:23
 */
public class SocketClient {
	public static void main(String[] args) {
		PrintWriter out;
		try {
			   System.out.println("Try to Connect to 127.0.0.1:10000");
			   Socket socket = new Socket("127.0.0.1",10000);
			   System.out.println("The Server Connected!");
			   System.out.println("Please enter some Character:");
			   BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
			   out = new PrintWriter(socket.getOutputStream(),true);
			   out.println(line.readLine());
			   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			   System.out.println(in.readLine());
			   out.close();
			   in.close();
			   socket.close();
			  }catch(IOException e) {
			   System.out.println("Wrong"+e);
			  }
	}
}
