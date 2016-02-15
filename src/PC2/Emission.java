package PC2;

import java.io.PrintWriter;

import javax.swing.JTextArea;


public class Emission{

	private PrintWriter out;
	private String message = null;
	//
	private JTextArea messageZone = null;
	private StringBuffer allMessage = null;
	
	public Emission(PrintWriter out, JTextArea messageZone, StringBuffer allMessage, String message) {
		this.out = out;
		this.messageZone = messageZone;
		this.allMessage = allMessage;
		this.message = message;		
	}

	
	public void Send() {
		
		//envoi du message crypte
		out.println(message);
		out.flush();
		
		//Affichage
		allMessage.append("Vous : " + message + "\n");
		messageZone.setText(allMessage.toString());
	}
}
