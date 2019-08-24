package com.dictionary.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

public class waiting {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waiting window = new waiting();
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
	public waiting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 661, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Notice panel
		JPanel noticePanel = new JPanel();
		noticePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		frame.getContentPane().add(noticePanel, BorderLayout.NORTH);
		noticePanel.setLayout(new GridLayout(2, 1, 0, 10));
		
		JLabel waitingNoticeLabel = new JLabel("You are waiting for a game.");
		waitingNoticeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		waitingNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noticePanel.add(waitingNoticeLabel);
		
		JPanel roomNamePanel = new JPanel();
		noticePanel.add(roomNamePanel);
		roomNamePanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel roomLabel = new JLabel("Game Room");
		roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomNamePanel.add(roomLabel);
		JLabel lobbyLabel = new JLabel("Game Lobby");
		lobbyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomNamePanel.add(lobbyLabel);
		
		//Text Area Panel
		JPanel textAreaPanel = new JPanel();
		textAreaPanel.setBorder(new EmptyBorder(5, 50, 0, 50));
		frame.getContentPane().add(textAreaPanel, BorderLayout.CENTER);
		textAreaPanel.setLayout(new GridLayout(1, 1, 0, 5));
		//Game room list
		JPanel roomListPanel = new JPanel();
		roomListPanel.setBorder(new EmptyBorder(0, 0, 0, 30));
		textAreaPanel.add(roomListPanel);
		roomListPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane roomScrollPane = new JScrollPane();
		roomListPanel.add(roomScrollPane);
		JTextArea RoomListArea = new JTextArea();
		roomScrollPane.setViewportView(RoomListArea);
		//Game lobby list
		JPanel lobbyListPanel = new JPanel();
		lobbyListPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		textAreaPanel.add(lobbyListPanel);
		lobbyListPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane lobbyScrollPane = new JScrollPane();
		lobbyListPanel.add(lobbyScrollPane);
		JTextArea lobbyListArea = new JTextArea();
		lobbyScrollPane.setViewportView(lobbyListArea);
		
		//Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(20, 80, 20, 80));
		buttonPanel.setLayout(new GridLayout(2, 1, 160, 0));
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		//invitation Panel
		JPanel invitationPanel = new JPanel();
		buttonPanel.add(invitationPanel);
		invitationPanel.setLayout(new GridLayout(0, 2, 0, 0));
		//mesage
		JLabel inviMessageLabel = new JLabel("You are invited to the game!");
		invitationPanel.add(inviMessageLabel);
		//invitation button panel
		JPanel invitationBtnPanel = new JPanel();
		invitationPanel.add(invitationBtnPanel);
		invitationBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//join button
		JButton Join = new JButton("Join");
		Join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		invitationBtnPanel.add(Join);
		//leave button
		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		invitationBtnPanel.add(btnLeave);
		
		//action panel
		JPanel actionBtnPanel = new JPanel();
		buttonPanel.add(actionBtnPanel);
		actionBtnPanel.setLayout(new GridLayout(0, 2, 120, 20));
		//invite button
		JButton btnInvite = new JButton("Invite");
		actionBtnPanel.add(btnInvite);
		actionBtnPanel.setEnabled(false);
		//start button
		JButton btnStart = new JButton("Start");
		actionBtnPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

}
