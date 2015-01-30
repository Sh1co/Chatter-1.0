package com.ja.simpleNetworking.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JTextArea;


public class ClientEngine {
	
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	Date d = new Date();
	boolean running;
	int port;
	String ip;
	JTextArea ja;
	
	
	public ClientEngine(String IP,int Port) {
		// TODO Auto-generated constructor stub
		port = Port;
		ip = IP;
		
		
	}
	
	/*public static void main(String [] args){
		ClientEngine ce = new ClientEngine();
		ce.start();
	}*/
	
	
	void start(JTextArea txt){
		running=true;
		 System.out.println(d+" Start method started");
		ja=txt;
		LoopThread LT=new LoopThread();
		Thread loop = new Thread(LT);
		loop.start();
		
	}
	void connect(JTextArea txt){
		try {
			socket = new Socket(InetAddress.getByName(ip),port);
			System.out.println(d+" Connected successfully");
			txt.append("\n"+d+" Connected successfully");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(d+" A problem happend while connecting");
			txt.append("\n"+d+" A problem happend while connecting");
		}
	}
	void setupStreams(JTextArea txt){
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
			System.out.println(d+" Streams Created successfully");
			txt.append("\n"+d+" Streams Created successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(d+" A problem happend while creating the streams");
			txt.append("\n"+d+" A problem happend while creating the streams");
			e.printStackTrace();
		}
		
		
		
		
	}
	void chatting(JTextArea txt){
		
		do{
	try {
		System.out.println((String)input.readObject());
		txt.append("\n"+(String)input.readObject());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		}while(running);	
		
	}
	void sendMessage(String msg){
		
		try {
			output.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cant send message");
		}
		
		
	}
	
	class LoopThread implements Runnable {
		public void run() {
			connect(ja);
			setupStreams(ja);
			chatting(ja);
		}
	}		
	
	

}
