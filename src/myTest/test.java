package myTest;


import  exception.*;
import gnu.io.SerialPort;
import myTest.*;
public class test {
      public static void main(String args[]){
    	  
    		  try {
        		 SerialManager.operateHand("Q", "5"); 
        	  }catch(Exception e) {
        		  e.printStackTrace();
        	  }
    	  }
   
}

