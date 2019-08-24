package com.dictionary.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Waiting for Players");
        frame.setBounds(100, 100, 450, 300);

        //Notice panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(3, 1, 0, 10));
        JLabel noticeLabel1 = new JLabel("You are waiting for a game.");
        noticeLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
        noticeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(noticeLabel1);
        JLabel noticeLabel2 = new JLabel("Joined players:");
        noticeLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        noticeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(noticeLabel2);


        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

//        JButton btnBack = new JButton("Back");
//        buttonPanel.add(btnBack);
//        btnBack.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            	action = Action.Cancel;
//                setVisible(false);
//            }
//        });

        //Start button for player who created the game
       JButton btnSubmit = new JButton("Start");
        buttonPanel.add(btnSubmit);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new EmptyBorder(0, 80, 0, 80));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
	}

}
