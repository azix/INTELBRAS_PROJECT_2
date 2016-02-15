package PC2;

import java.net.*;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import PC2.Emission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Communication implements Runnable {

	private Socket socket = null;
	public static Thread t1,t2;
	public static String login = null, pass = null, message1 = null, message2 = null, message3 = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	//
	private JTextArea messageZone = null;
	private JTextField messageField = null;
	private JButton boutonSend;
	private StringBuffer allMessage = new StringBuffer();
	
	public Communication(Socket s, JTextArea messageZone, JTextField messageField, JButton boutonSend){	
		socket = s;
		 this.messageZone = messageZone;
		 this.messageField = messageField;
		 this.boutonSend = boutonSend;
	}
	
	public void run() {
		
		try {
			allMessage.append(messageZone.getText() + "\n\n");
			
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
			/*Reception des message*/
			t1 = new Thread(new Reception(in, messageZone, allMessage));
			t1.start();
			
			/*Emission du message si on click sur Envoyer */
			boutonSend.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Emission send= new Emission(out, messageZone, allMessage, messageField.getText());
					send.Send();
					messageField.setText("");
				}
	        });
			
		} catch (IOException e) {
			System.err.println("Le serveur ne r�pond plus ");
		}
	}

}
