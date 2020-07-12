package Project;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
public static void main(String[] args) throws ClassNotFoundException {
    	
    	System.out.println("Server Started");
    	DatagramSocket aSocket = null;
    	
      try {
        aSocket = new DatagramSocket(1257);
        byte[] buffer = new byte[1024];

        while(true) {
          DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
          System.out.println("Waiting for request...");
          aSocket.receive(reply);
          
          Request read = (Request) Tools.deserialize(reply.getData());
          System.out.println("Received request \n");
          System.out.println(read.toString());
          
          String feedback = "";
		  File dir = new File("C:/Users/manie/Desktop/infa/lab2/");
		  File[] files = null;
		  
		  switch (read.getType()){
			
		  
			case HourChannelSchedule:
				feedback = new String("Server received and saved object");
				break;

			case ChannelSchedule:

		        feedback = "\n Files found: ";
				
				break;
				
			case HourSchedule:

		        	feedback = "Didn't find any matching files";
		        	break;
			}
		  DatagramPacket reply1 = new DatagramPacket(feedback.getBytes(), feedback.length(), 
					reply.getAddress(), reply.getPort());
          aSocket.send(reply1); 

        }
      } catch (SocketException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
				aSocket.close();
			}
      
    }
}
