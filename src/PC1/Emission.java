package PC1;

import java.io.PrintWriter;

import javax.swing.JTextArea;


public class Emission{

	private PrintWriter out;
	private String message = null;
	//
	private JTextArea messageZone = null;
	private StringBuffer toutMessage = null;
	
	public Emission(PrintWriter out, JTextArea messageZone, StringBuffer toutMessage, String message) {
		this.out = out;
		this.messageZone = messageZone;
		this.toutMessage = toutMessage;
		this.message = message;		
	}

	
	public void Send() {
		
		//envoi du message crypte
		out.println(message);
		out.flush();
		
		//Affichage
		toutMessage.append("Vous : " + message + "\n");
		messageZone.setText(toutMessage.toString());

	}
}
