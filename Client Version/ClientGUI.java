package com.ja.simpleNetworking.Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_input;
	private JTextArea txt_output;
	private JButton btn_Send;
	ClientEngine CE;
	private JTextField txt_IP;
	private JTextField txt_Port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_output = new JTextArea();
		txt_output.setEditable(false);
		txt_output.setBounds(10, 11, 364, 180);
		contentPane.add(txt_output);
		
		btn_Send = new JButton("Send");
		btn_Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CE.sendMessage("\n Client : "+txt_input.getText());
			}
		});
		btn_Send.setBounds(10, 233, 364, 17);
		contentPane.add(btn_Send);
		
		txt_input = new JTextField();
		txt_input.setBounds(10, 202, 364, 20);
		contentPane.add(txt_input);
		txt_input.setColumns(10);
		
		txt_IP = new JTextField();
		txt_IP.setBounds(384, 27, 86, 20);
		contentPane.add(txt_IP);
		txt_IP.setColumns(10);
		
		txt_Port = new JTextField();
		txt_Port.setBounds(384, 76, 86, 20);
		contentPane.add(txt_Port);
		txt_Port.setColumns(10);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(384, 10, 46, 14);
		contentPane.add(lblIp);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(384, 58, 46, 14);
		contentPane.add(lblPort);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CE = new ClientEngine(txt_IP.getText(), Integer.parseInt(txt_Port.getText()));
				CE.start(txt_output);
				
				
			}
		});
		btnStart.setBounds(384, 107, 89, 23);
		contentPane.add(btnStart);
	}
}
