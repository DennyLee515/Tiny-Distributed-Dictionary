/**
 * COMP90015 Distributed System
 * Assignment 1 multi-thread dictionary server
 * Tutor:Minxian Xu
 * Name: Dongming Li
 * Student ID:1002971
 * Email：dongming@student.unimelb.edu.au
 */

package com.dictionary.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}


	public synchronized void run() {
		InputStream is = null;
		OutputStream os = null;
		DataOutputStream dop = null;
		DataInputStream dip = null;
		String output = null;
		String info = null;
		try {
			// input stream, get info from client

			is = socket.getInputStream();
			dip = new DataInputStream(is);

			Repository dictionary = new Repository();

			while (!(info = dip.readUTF()).equals("")) {

				// Enquire method
				if (info.substring(0, 3).equals("en/")) {
					String word = info.substring(3);
					System.out.println(word);
					if (!dictionary.checkWordExists(word)) {
						output = "The word does not exist! You can add it in the ADD page!";
					} else {
						output = dictionary.findByWord(word);
					}

					// add method
				} else if (info.substring(0, 3).equals("ad/")) {
					int offset = info.indexOf("?");
					String word = info.substring(3, offset);
					String meaning = info.substring(offset + 1);

					System.out.println("word:" + word + " meaning:" + meaning);
					if (dictionary.checkWordExists(word)) {
						output = "This word already exists! Please remove it before adding.";
					} else {
						dictionary.setWord(word, meaning);
						output = "You have added the word \"" + word + "\" and its meaning \"" + meaning + "\" !";
					}

					// delete method
				} else if (info.substring(0, 3).equals("de/")) {
					String word = info.substring(3);
					if (dictionary.checkWordExists(word)) {
						dictionary.deleteByWord(word);
						output = "You have deleted the word \"" + word + "\"!";
					} else {
						output = "The word does not exist!";
					}
//					if (!dictionary.checkWordExists(word)) {
//						output = "You have deleted the word \"" + word + "\"!";
//					} else {
//						output = "Delete Failed!";
//					}
				}else{
					output = "Invalid Request!";
				}
				// get an output stream, acknowledge request from client
				os = socket.getOutputStream();
				dop = new DataOutputStream(os);
				dop.writeUTF(output);

				System.out.println(dictionary.getDictionary());
				System.out.println(output);
			}
		} catch(EOFException e) {
			System.out.println("Client closed!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			try {
				// release resources
				if (dop != null)
					dop.close();
				if (dip != null)
					dip.close();
				if (os != null)
					os.close();
				if (is != null)
					is.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
