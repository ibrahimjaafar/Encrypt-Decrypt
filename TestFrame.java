//Import packages
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;


public class TestFrame{

	static JFrame frame1;
	static Container pane;
	static JButton HashButton, ExitButton;
	static JLabel msgEnter, macOutput;
	static JTextField textMessage;
	static Insets insets;
	static String hash = "";
	static JTextField result;
	public static void main (String args[]){

		//Create the frame
		frame1 = new JFrame ("HMAC MD5 Simulator");
		frame1.setSize (700,200);
		pane = frame1.getContentPane();
		insets = pane.getInsets();
		pane.setLayout (null);

		//Create buttons
		HashButton = new JButton ("Hash");
		ExitButton = new JButton ("Exit");
		msgEnter = new JLabel ("Enter Message:");
		macOutput = new JLabel ("Hash:");
		textMessage = new JTextField (20);

		pane.add (msgEnter);
		pane.add (textMessage);
		pane.add (HashButton);
		pane.add (ExitButton);
		pane.add(macOutput);
		pane.setBackground(Color.YELLOW);
		macOutput.setBounds (insets.left + 230, insets.top + 55, msgEnter.getPreferredSize().width, msgEnter.getPreferredSize().height);

		msgEnter.setBounds (insets.left + 200, insets.top + 5, msgEnter.getPreferredSize().width, msgEnter.getPreferredSize().height);
		textMessage.setBounds (msgEnter.getX() + msgEnter.getWidth() + 5, insets.top + 5, textMessage.getPreferredSize().width, textMessage.getPreferredSize().height);
		HashButton.setBounds (textMessage.getX() + textMessage.getWidth() + 5, insets.top + 5, HashButton.getPreferredSize().width, HashButton.getPreferredSize().height);
		ExitButton.setBounds (insets.left + 465, msgEnter.getY() + msgEnter.getHeight() + 75, ExitButton.getPreferredSize().width, ExitButton.getPreferredSize().height);
		textMessage.setBackground(Color.lightGray);
		//Set frame visible
		frame1.setVisible (true);

		
		HashButton.addActionListener(new HashButtonAction()); 
		ExitButton.addActionListener(new ExitAction());
	}
	/**
	 * This function uses a key and algorithm to produce an Hmac message digest
	 * @param msg
	 * @param keyString
	 * @param algo
	 * @return returns the Hashed Message
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String HMAC(String msg, String keyString, String algo)
			 throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	 {
		      String MessageHash = "";
		      SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), algo);
		      Mac mac = Mac.getInstance(algo);
		      mac.init(key);
		      byte[] bytes = mac.doFinal(msg.getBytes());
		      
		      StringBuffer hash = new StringBuffer();
		      
		      //Convert Bytes to Hex
		      for(int i=0; i < bytes.length; i++){
		    	   		hash.append(Character.forDigit((bytes[i] >> 4) & 0xF, 16));
		    	   		hash.append(Character.forDigit((bytes[i] & 0xF), 16));
		    	}
		      
		   
		      MessageHash = hash.toString();
		 
		    return MessageHash;
		  }
	/**
	 * 
	 *Produces hash when Hash is clicked.
	 *
	 */
	 public static class HashButtonAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			 result = new JTextField ();
			 try {
				result.setText(HMAC(textMessage.getText(), "KEY", "HmacMD5"));
			} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
		     result.setBounds (msgEnter.getX() + msgEnter.getWidth() + 5, insets.top + 55, result.getPreferredSize().width + 20, result.getPreferredSize().height);
		     result.setBackground(Color.yellow);
		     result.setForeground(Color.blue.darker());
		     pane.add(result);
		}
	}
	public static class ExitAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
		System.exit(0);
		}
		
	}

}
