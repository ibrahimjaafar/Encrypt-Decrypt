
import javax.swing.JOptionPane; // USING JOPTIONPANE GUI

public class HillCipher {
    //Declare and Initialize the matrices and the Key

	public static int[][] matrix = new int[][]{
                { 1, 2, 2 },
                { 1, 3, 2 },
                { 1, 2, 3 },
            };
     public static int[][] inverseofMatrix = new int[][]{
                { 5, -2, -2 },
                { -1, 1, 0 },
                { -1, 0, 1},
            };
    
            public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
   
     public static void main(String[] args) {
       
    	// declare variables here
        String msg,resultStr ="";
        int ch, rm, result, restart;
        restart = 0;
        while (restart == 0){
        	//ASK FOR INPUTS HERE
        ch = JOptionPane.showOptionDialog(null, "Select option", "Hill Cipher 3x3", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] { "Encrypt", "Decrypt" }, JOptionPane.NO_OPTION);
        msg = JOptionPane.showInputDialog(null, "Enter plain/cipher message below");
        msg = msg.toUpperCase();
        
        rm = msg.length() % 3;        // Checks to make sure that string is groups of 3 characters     
        if(rm!=0){
            for(int i = 1; i<= (3-rm);i++){
                msg= msg + 'Z';
            }
        }
      
        char[] str = msg.toCharArray(); // SET PLAIN text AS A MATRIX e.g abc = [a]
        								//										 [b]
        								//                                       [c]
        	if (ch == JOptionPane.YES_OPTION){ // Check what the user selected (encrypt/decrypt)

                    for(int i=0;i< msg.length(); i+=3){
                        resultStr += encrypt(str[i],str[i+1],str[i+2]); // Apply the method encrypt to the plain text
                    }
                   }
                    else if (ch == JOptionPane.NO_OPTION){
                    for(int i=0;i< msg.length(); i+=3){
                        resultStr += decrypt(str[i],str[i+1],str[i+2]); // Apply decrypt () to the cipher text
                    }
                   }
                  
        
        
         result = JOptionPane.showOptionDialog(null, "Result: "+ resultStr, "RESULT", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] { "Back", "Close" }, JOptionPane.NO_OPTION);
         if (result != JOptionPane.YES_OPTION) { // Restart program if user selects Back
         restart = 1;
        }
        
        resultStr = ""; // reinitialize string
    
        }
    }

       public static String encrypt(char a, char b, char c) { //  Encrypt the string using hill cipher
            String resultString = "";
            int x,y, z;
           //Multiply the key matrix with the plain text matrix (split by 3 as done above so 3x3 * 1x3)
            // SAME THING APPLIES TO decrypt() except use inverse key
            x = ((int)a - 65) * matrix[0][0] + ((int)b - 65) * matrix[1][0] + ((int)c - 65) * matrix[2][0];
            y = ((int)a - 65) * matrix[0][1] + ((int)b - 65) * matrix[1][1] + ((int)c - 65) * matrix[2][1];
            z = ((int)a - 65) * matrix[0][2] + ((int)b - 65) * matrix[1][2] + ((int)c - 65) * matrix[2][2];
            if ((x%26) < 0)													
         	   a = key.charAt(26+x%26);        //mod 26 on the result above to get the encrypted letters
            else 
         	   a = key.charAt(x%26);
            if ((y%26) < 0)
         	   b = key.charAt(26+y%26);
            else 
         	   b = key.charAt(y%26);
            if ((z%26) < 0)
         	   c = key.charAt(26+z%26);
            else 
         	  c = key.charAt(z%26);
            resultString = "" + a + b + c;
            return resultString;
        }

       public static String decrypt(char a, char b, char c) { // Decrypt the string with the inversed matrix key
        	int x,y,z;
        	String resultString = "";
            x = ((int)a - 65) * inverseofMatrix[0][0] + ((int)b - 65) * inverseofMatrix[1][0] + ((int)c - 65) * inverseofMatrix[2][0];
            y = ((int)a - 65) * inverseofMatrix[0][1] + ((int)b - 65) * inverseofMatrix[1][1] + ((int)c - 65) * inverseofMatrix[2][1];
            z = ((int)a - 65) * inverseofMatrix[0][2] + ((int)b - 65) * inverseofMatrix[1][2] + ((int)c - 65) * inverseofMatrix[2][2];
           if ((x%26) < 0)
        	   a = key.charAt(26+x%26);
           else 
        	   a = key.charAt(x%26);
           if ((y%26) < 0)
        	   b = key.charAt(26+y%26);
           else 
        	   b = key.charAt(y%26);
           if ((z%26) < 0)
        	   c = key.charAt(26+z%26);
           else 
        	  c = key.charAt(z%26);
       
            resultString = "" + a + b + c;
            return resultString;
        }  
}
