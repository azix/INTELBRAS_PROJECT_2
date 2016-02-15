package PC1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PC1Serveur{

	public static ServerSocket socketserver = null;
	public static Thread t;
	public Thread tReception;
	public static Thread tEmission;
	
	// Various GUI components and info
	public static JFrame mainFrame = null;
	public static JTextArea messageZone = null;
	public static JTextField messageField = null;
	public static JButton boutonSend = null;
	public static String message = null;
	
	// Information de connexion
	public static String adresseIP = "localhost";
	public static int port = 1234;

	public static void initGUI(){
	   
		// preparation du panel d'envoi de message
		JPanel messagePane = new JPanel(new BorderLayout());
		messagePane.setBorder(new EmptyBorder(5, 0, 0, 0));
		messageField = new JTextField();
		boutonSend = new JButton("Send");
		messagePane.add(messageField, BorderLayout.CENTER);
		messagePane.add(boutonSend, BorderLayout.EAST);
	   
		// preparation du panel de chat
		JPanel chatPane = new JPanel(new BorderLayout());  
		chatPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		messageZone = new JTextArea(10, 20);
		messageZone.setLineWrap(true);
		messageZone.setEditable(false);
		JScrollPane chatTextPane = new JScrollPane(messageZone,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatPane.add(messagePane, BorderLayout.SOUTH);
		chatPane.add(chatTextPane, BorderLayout.CENTER);
		chatPane.setPreferredSize(new Dimension(400, 400));

		// preparation du panel global
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.add(chatPane, BorderLayout.CENTER);

		// preparation de la fenetre
		mainFrame = new JFrame("Ordinateur 1 (serveur)");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(mainPane);
		mainFrame.setSize(chatPane.getPreferredSize());
		mainFrame.setLocation(400, 400);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public static void main(String args[]) {
		
		initGUI();
		try {
			socketserver = new ServerSocket(2009);
			
			messageZone.setText("Computer 1 is listening to  "+socketserver.getLocalPort());
			
			t = new Thread(new Communication(socketserver, messageZone, messageField, boutonSend));
			t.start();
			
		} catch (IOException e) {
			System.err.println("Erreur serveur");
		}
	}

}

