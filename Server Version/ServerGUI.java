package com.ja.simpleNetworking;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;

import java.awt.Scrollbar;

import javax.swing.UIManager;

public class ServerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Input;
	public ServerEngine se = new ServerEngine(1218);
	private JTextArea txt_Output;
	private JButton btnOn;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI frame = new ServerGUI();
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
	public ServerGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				se.start(txt_Output);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 280, 282);
		contentPane.add(scrollPane);
		
		txt_Output = new JTextArea();
		scrollPane.setViewportView(txt_Output);
		txt_Output.setEditable(false);
		
		
		
		
		
		txt_Input = new JTextField();
		txt_Input.setBounds(0, 293, 300, 20);
		contentPane.add(txt_Input);
		txt_Input.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = txt_Input.getText();
				if(message == "")txt_Input.setText("");
				else se.sendMessage("\n Server : "+message);
				
				txt_Input.setText("");
			}
		});
		btnSend.setBounds(0, 324, 300, 23);
		contentPane.add(btnSend);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(284, 0, 50, 20);
		contentPane.add(lblStatus);
		
		btnOn = new JButton("On");
		btnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnOn.getText() == "On"){
					se.stop(txt_Output);
					btnOn.setText("Off");
				}else{
					se.start(txt_Output);
					btnOn.setText("On");
				}
				
			}
		});
		btnOn.setBounds(278, 22, 56, 23);
		contentPane.add(btnOn);
	}
}
