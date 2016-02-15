package PC1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Communication implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private Thread tReception;
	
	//
	private JTextArea messageZone = null;
	private JTextField messageField = null;
	private JButton boutonSend;
	private StringBuffer allMessage = new StringBuffer();
	
	public Communication(ServerSocket ss, JTextArea messageZone, JTextField messageField, JButton boutonSend){
	 socketserver = ss;
	 this.messageZone = messageZone;
	 this.messageField = messageField;
	 this.boutonSend = boutonSend;
	}
	
	public Socket getSocket(){
		return (this.socket);
	}
	
	public void run() {
		
		try {
			while(true){
				socket = socketserver.accept();
				
				allMessage.append(messageZone.getText() + "\nOrdinateur 2 vient de se connecter\n\n");
				messageZone.setText(allMessage.toString());
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				
				/*Emission du message si on click sur Envoyer */
				boutonSend.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Emission send = new Emission(out, messageZone, allMessage, messageField.getText());
						send.Send();
						messageField.setText("");
					}
		        });	
				
				/*Reception des message*/
				tReception = new Thread(new Reception(in, messageZone, allMessage));
				tReception.start();
			}
		} catch (IOException e) {
			
			System.err.println("Erreur serveur");
		}
		
	}
}
