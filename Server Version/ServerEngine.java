package com.ja.simpleNetworking;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JTextArea;


public class ServerEngine {
	
	
	ServerSocket SSocket;
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	boolean running;
	Date d = new Date();
	int port;
	JTextArea ja;
	
	
	
	
	
	
	public ServerEngine(int port) {
		this.port=port;
		
	}
	
	
	
	
	


void start(JTextArea txt){
	running = true;
	try {
		SSocket = new ServerSocket(port,100);
		System.out.println(d+" Server Socket Created\n");
		txt.append(d+" Server Socket Created\n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(d+" Error Happend While Creating\n");
		txt.append(d+" Error Happend While Creating\n");
	}
	ja=txt;
	LoopThread LT=new LoopThread();
	Thread loop = new Thread(LT);
	loop.start();
	
}
void clientWaiting(JTextArea txt){
	System.out.println(d+" Waiting for Clients\n");
	txt.append(d+" Waiting for Clients\n");
	try {
		socket = SSocket.accept();
		System.out.println(d+" Accepted Client!\n");
		txt.append(d+" Accepted Client!\n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(d+" Something wrong happend while accepting client\n");
		txt.append(d+" Something wrong happend while accepting client\n");
	}
}
void setupStreams(JTextArea txt){

	  try {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		System.out.println(d+" Output stream created and flushed successfully\n");
		txt.append(d+" Output stream created and flushed successfully\n");
		input = new ObjectInputStream(socket.getInputStream());
		System.out.println(d+" Input Stream created successfully\n");
		txt.append(d+" Input Stream created successfully\n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(d+" Something went wrong with the creation of the input or output streams\n");
		txt.append(d+" Something went wrong with the creation of the input or output streams\n");
	}
	  
	  
		
		
	
}
void chatting(JTextArea txt){
	String msg = "Welcome,Your connected!\n";
	sendMessage(msg);
	System.out.println("Started Chatting\n");
	do{
		try {
			msg=(String)input.readObject();
			System.out.println(d+" "+msg);
			txt.append(d+" "+msg);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}while(running);
	
	
	
}
void stop(JTextArea txt){
	running = false;
	try {
		output.close();
		input.close();
		SSocket.close();
		
		System.out.println(d+" Connection closed successfully ");
		txt.append(d+" Connection closed successfully ");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(d+" Error happend while closing");
		txt.append(d+" Error happend while closing");
		
	}
}
void sendMessage(String message){
	try {
		output.writeObject(message);
		output.flush();
		System.out.println(d+" Server : "+message+"\n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(d+" Error while sending message \n");
	}
}
class LoopThread implements Runnable {
	public void run() {
		while(running){
			clientWaiting(ja);
			setupStreams(ja);
			chatting(ja);
		}	
	}
}
}
