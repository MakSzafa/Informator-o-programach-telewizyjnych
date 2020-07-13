package Project;

import java.awt.Window.Type;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

	public static void main(String[] args) throws ClassNotFoundException {
		
		DatagramSocket aSocket = null;
		int serverPort = 1257;
		
		System.out.println("Client Started");

		Request object = new Request(1,"15:00",Request.Type.ChannelSchedule);
		
		try {
			
			byte[] data = Tools.serialize(object);
			byte[] receivedData = new byte[1024];
			aSocket = new DatagramSocket();
			InetAddress aHost = InetAddress.getLocalHost();

				DatagramPacket request = new DatagramPacket(data,data.length,aHost,serverPort);
				aSocket.send(request);
				System.out.println("Sending request");
				
				DatagramPacket reply = new DatagramPacket(receivedData, receivedData.length);
				aSocket.receive(reply);
				
				String t = new String(reply.getData(), 0, reply.getLength());
			    System.out.println(t);
				
		} catch (SocketException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			aSocket.close();
		}
	}
}
