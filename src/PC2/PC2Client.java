package PC2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PC2Client{

	public static Socket socket = null;
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
		mainFrame = new JFrame("Ordinateur 2 (Client)");
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
			messageZone.setText("Ask connexion to computer 1");
			socket = new Socket("127.0.0.1",2009);
			messageZone.setText(messageZone.getText() + "\n Connexion established with computer 1\n\n");
			
			t = new Thread(new Communication(socket, messageZone, messageField, boutonSend));
			t.start();
			
		}catch (UnknownHostException e) {
			  System.err.println("Impossible to connect "+socket.getLocalAddress());
		} catch (IOException e) {
		  System.err.println("Computer 1 it's not listening "+socket.getLocalPort());
		}
	}

}

