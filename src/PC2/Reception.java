package PC2;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	//
	private JTextArea messageZone = null;
	private StringBuffer allMessage = null;
	
	public Reception(BufferedReader in, JTextArea messageZone, StringBuffer allMessage){
		this.in = in;
		this.messageZone = messageZone;
		this.allMessage = allMessage;
	}
	
	public void run() {
		
		while(true){
	        try { 	
	        //Reception du message crypte
		    message = in.readLine();
		        
		    allMessage.append("Ordinateur 1 : " + message + "\n");
	        messageZone.setText(allMessage.toString());
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
