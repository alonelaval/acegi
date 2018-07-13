package com.alonelaval.acegi.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.test.SocKetTest.java
 * @createDate 2009-6-12 下午02:03:17	
 */
public class SocKetServer {
	public static void main(String[] args) {
		PrintWriter out;
		try {
			   ServerSocket ss = new ServerSocket(10000);
			   while(true) {
			    Socket socket = ss.accept();
			    String RemoteIP = socket.getInetAddress().getHostAddress();
			    String RemotePort = ":"+socket.getLocalPort();
			    System.out.println("A client come in!IP:"+RemoteIP+RemotePort);
			    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    String line = in.readLine();
			    System.out.println("Cleint send is :" + line);
			    out = new PrintWriter(socket.getOutputStream(),true);
			    out.println("Your Message Received!");
			    out.close();
			    in.close();
			    socket.close();
			   }
			  }catch (IOException e) {
				  System.out.println(e);
			  }
	}
}
