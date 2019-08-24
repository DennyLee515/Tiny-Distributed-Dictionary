/**
 * COMP90015 Distributed System
 * Assignment 1 multi-thread dictionary server
 * Tutor:Minxian Xu
 * Name: Dongming Li
 * Student ID:1002971
 * Email：dongming@student.unimelb.edu.au
 */

package com.dictionary.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


public class Server {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
    	String in = null;
    	int port=8971;
		System.out.println("Please input port number(default:8971):");
		Scanner sc = new Scanner(System.in);
		if(!(in = sc.nextLine()).equals("")) {
			port = Integer.parseInt(in);
		}
		sc.close();
        try {
            //create a server socket, define its port
            ServerSocket serverSocket=new ServerSocket(port);
            Socket socket=null;
          
            String ip = serverSocket.getInetAddress().toString();
            port = serverSocket.getLocalPort();
            System.out.println("Server Started! Server IP:"+ip+". Server port:"+port+".\nWaiting for connection......");
            //keep listening 
            while(true){
                //accept() method, waiting for client
                socket=serverSocket.accept();
                //when a client requests, create a new server thread
                ServerThread serverThread=new ServerThread(socket);
                serverThread.start();

                InetAddress address=socket.getInetAddress();
                System.out.println("A client connected!\nClient IP:"+address.getHostAddress());
            }

        }catch(SocketException se){
        	System.out.println("There is already a Server!");
        } catch (Exception e) {
        	System.out.println("Unknow exception");
            e.printStackTrace();       
        }
    }
}