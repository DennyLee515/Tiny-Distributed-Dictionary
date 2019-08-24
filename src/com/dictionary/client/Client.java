/**
 * COMP90015 Distributed System
 * Assignment 1 multi-thread dictionary server
 * Tutor:Minxian Xu
 * Name: Dongming Li
 * Student ID:1002971
 * Email：dongming@student.unimelb.edu.au
 */

package com.dictionary.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

			//input IP address from console 
			String ip = "127.0.0.1";
			String ipIn = null;
			int port = 8971;
			String portIn = null;
			System.out.println("Please input IP address(default:127.0.0.1):");
			Scanner sc = new Scanner(System.in);
			if (!(ipIn = sc.nextLine()).equals("")) {
				ip = ipIn;
			}
			System.out.println("Please input port number: (default:8971):");
			
			if(!(portIn = sc.nextLine()).equals("")) {
				port = Integer.parseInt(portIn);
			}
			
			//Create GUI frame
			JFrame frame = new JFrame();
			frame.setBounds(100, 100, 458, 494);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);

			JLabel inquireLabel = new JLabel("Please input the word you want to query:");
			inquireLabel.setBounds(10, 10, 434, 15);
			frame.getContentPane().add(inquireLabel);

			JTextField inquireTextField = new JTextField();
			inquireTextField.setBounds(10, 30, 283, 21);
			frame.getContentPane().add(inquireTextField);
			inquireTextField.setColumns(10);

			JLabel addLabel = new JLabel("Please input the word you want to add:");
			addLabel.setBounds(10, 113, 401, 15);
			frame.getContentPane().add(addLabel);

			JTextField addTextField = new JTextField();
			addTextField.setBounds(10, 138, 283, 21);
			frame.getContentPane().add(addTextField);
			addTextField.setColumns(10);

			JLabel lblTheMeaningOf = new JLabel("The meaning of the word is:");
			lblTheMeaningOf.setBounds(10, 169, 283, 15);
			frame.getContentPane().add(lblTheMeaningOf);

			JTextField meaningTextField = new JTextField();
			meaningTextField.setBounds(10, 194, 283, 21);
			frame.getContentPane().add(meaningTextField);
			meaningTextField.setColumns(10);

			JLabel deleteLabel = new JLabel("Please input the word you want to delete:");
			deleteLabel.setBounds(10, 275, 283, 15);
			frame.getContentPane().add(deleteLabel);

			JTextField deleteTextField = new JTextField();
			deleteTextField.setBounds(10, 300, 283, 21);
			frame.getContentPane().add(deleteTextField);
			deleteTextField.setColumns(10);

			JTextArea outputTextArea = new JTextArea();
			outputTextArea.setBounds(10, 358, 421, 87);
			frame.getContentPane().add(outputTextArea);
			frame.setVisible(true);

		try {
			// create socket, assign ip address and port
			Socket socket = new Socket(ip, port);
			// get output stream, send info to server
			OutputStream os = socket.getOutputStream();;
			DataOutputStream dop = new DataOutputStream(os);
			InputStream is = socket.getInputStream();

			JButton btnQuery = new JButton("Query");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String output = null;
					if ((!deleteTextField.getText().equals("")) || (!addTextField.getText().equals(""))
							|| (!meaningTextField.getText().equals(""))) {
						outputTextArea.setText("Please clean the delete and add text field!");
					} else if ((inquireTextField.getText().equals(""))) {
						outputTextArea.setText("The word you want to inquire is required!");
					} else if (!(inquireTextField.getText().matches("[a-zA-Z]+"))){
						outputTextArea.setText("Invalid put! Please input characters.");
					}else{
						output = "en/" + inquireTextField.getText();
					try {
						dop.writeUTF(output);
						DataInputStream dis = new DataInputStream(is);
						String info = null;
						info = dis.readUTF();
						outputTextArea.setText(info);
					} catch (IOException e) {
						e.printStackTrace();
						outputTextArea.setText("Failed to request server!");
					}
					}
				}
			});
			btnQuery.setBounds(318, 29, 93, 23);
			frame.getContentPane().add(btnQuery);
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((!inquireTextField.getText().equals("")) || (!deleteTextField.getText().equals(""))) {
						outputTextArea.setText("Please clean the query  and delete text field!");
					} else if ((addTextField.getText().equals("")) || (meaningTextField.getText().equals(""))) {
						outputTextArea.setText("The word and meaning text fields are both required!");
					} else if ((!(addTextField.getText().matches("[a-zA-Z]+")))){
						outputTextArea.setText("Invalid put! Please input characters.");
					}else{
						String output = "ad/" + addTextField.getText() + "?" + meaningTextField.getText();
						try {
							dop.writeUTF(output);
							DataInputStream dis = new DataInputStream(is);
							String info = null;
							info = dis.readUTF();
							outputTextArea.setText(info);
						} catch (IOException e1) {
							e1.printStackTrace();
							outputTextArea.setText("Failed to request server!");
						}
					}
				}
			});
			btnAdd.setBounds(318, 193, 93, 23);
			frame.getContentPane().add(btnAdd);

			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((!inquireTextField.getText().equals("")) || (!addTextField.getText().equals(""))
							|| (!meaningTextField.getText().equals(""))) {
						outputTextArea.setText("Please clean the query  and add text field!");
					} else if ((deleteTextField.getText().equals(""))) {
						outputTextArea.setText("The word you want to delete is required!");
					} else if (!(deleteTextField.getText().matches("[a-zA-Z]+"))){
						outputTextArea.setText("Invalid put! Please input characters.");
					}else{
						String output = "de/" + deleteTextField.getText();
						try {
							dop.writeUTF(output);
							//transfer input stream to data input stream
							DataInputStream dis = new DataInputStream(is);
							String info = null;
							info = dis.readUTF();
							outputTextArea.setText(info);
						} catch (IOException e1) {
							e1.printStackTrace();
							outputTextArea.setText("Failed to request server!");
						}

					}
				}
			});
			btnDelete.setBounds(318, 299, 93, 23);
			frame.getContentPane().add(btnDelete);

			// 4 close resources
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					super.windowClosing(e);
					try {
						is.close();
						dop.close();
						os.close();
						socket.close();
					} catch (IOException e1) {
						System.out.println("Abnormal closedown!");
						e1.printStackTrace();
					}

				}
			});
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Connect Failed! Please check server condition or the IP address!");
			outputTextArea.setText("Connect Failed! Please check server condition or the IP address!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Unknow exception!");
		}
	}

}