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

		String hourSchedule[] = {"6:00", "7:00", "8:00", "9:00", "10:00", "11:00",
				"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};

		// channel 1
		String programName1[] = {"Agenci NCIS","The Brave","MacGyver","Agenci NCIS","Agenci NCIS","Absentia",
				"Absentia","Absentia","The Brave","The Brave","MacGyver","Agenci NCIS","Agenci NCIS"};
		String description1[] = {"Season: 12   Episode: 10","Season: 1   Episode: 1","Season: 6   Episode: 3",
				"Season: 12   Episode: 11","Season: 12   Episode: 12","Season: 2   Episode: 4",
				"Season: 2   Episode: 5","Season: 2   Episode: 6","Season: 1   Episode: 2",
				"Season: 1   Episode: 3","Season: 6   Episode: 4","Season: 12   Episode: 13","Season: 12   Episode: 14"};
		// channel 2
		String programName2[] = {"Brudna robota","Brudna robota","Brudna robota","Militaria na warsztat",
				"Militaria na warsztat","Drwal zawodowiec","Drwal zawodowiec","Drwal zawodowiec",
				"Uliczne wyscigi","Uliczne wyscigi","Zwykle rzeczy","Zwykle rzeczy","Uliczne wyscigi"};
		String description2[] = {"Episode: 8","Episode: 9","Episode: 10","Episode: 18","Episode: 19",
				"Episode: 26","Episode: 27","Episode: 28","Episode: 2","Episode: 3","Episode: 34",
				"Episode: 35","Episode: 4"};
		// channel 3
		String programName3[] = {"Glowka pracuje","Glowka pracuje","Supa Strikas","Jedenastka","Supa Strikas",
				"Jedenastka","Jedenastka","Falsyfikot","Falsyfikot","Vampirina","Vampirina",
				"Lwia Straz","Lwia Straz"};
		String description3[] = {"Episode: 42","Episode: 43","Episode: 12","Episode: 87","Episode: 13",
				"Episode: 88","Episode: 89","Episode: 1","Episode: 2","Episode: 14","Episode: 15",
				"Episode: 122","Episode: 123"};
		// channel 4
		String programName4[] = {"Trudne Sprawy","Trudne Sprawy","Dlaczego Ja?","Szkola","Trudne Sprawy",
				"Dlaczego Ja?","Dlaczego Ja?","Szkola","Szkola","Ukryta prawda","Trudne Sprawy",
				"Ukryta prawda","Ukryta prawda"};
		String description4[] = {"Episode: 422","Episode: 423","Episode: 621","Episode: 123","Episode: 424",
				"Episode: 622","Episode: 623","Episode: 124","Episode: 125","Episode: 246","Episode: 425",
				"Episode: 247","Episode: 248"};

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
				System.out.println("Received request ");
				System.out.println(read.toString());

				String feedback = "";

				switch (read.getType()){

				case HourChannelSchedule:

					feedback = "\n Program on channel " + read.getChannelNr() + " at " 
							+ read.getHour() + " hour:" + "\n\tPROGRAM NAME:" + "\t" + "\tDESCRIPTION:"; 

					switch(read.getChannelNr()) {

					case 1:
						for (int i=0; i<hourSchedule.length; i++){
							if(hourSchedule[i].equals(read.getHour())){
								feedback = feedback + "\n\t" + programName1[i] + "\t\t" + description1[i];
							}
						}
						break;
					case 2:
						for (int i=0; i<hourSchedule.length; i++){
							if(hourSchedule[i].equals(read.getHour())){
								feedback = feedback + "\n\t" + programName2[i] + "\t\t" + description2[i];
							}
						}
						break;
					case 3:
						for (int i=0; i<hourSchedule.length; i++){
							if(hourSchedule[i].equals(read.getHour())){
								feedback = feedback + "\n\t" + programName3[i] + "\t\t" + description3[i];
							}
						}
						break;
					case 4:
						for (int i=0; i<hourSchedule.length; i++){
							if(hourSchedule[i].equals(read.getHour())){
								feedback = feedback + "\n\t" + programName4[i] + "\t\t" + description4[i];
							}
						}
						break;
					}
					break;

				case ChannelSchedule:

					feedback = "\n Daily schedule for channel " + read.getChannelNr() + ":\n" 
							+ "\tHOUR:" + "\t" + "\tPROGRAM NAME:" + "\t" + "\tDESCRIPTION:"; 

					switch(read.getChannelNr()) {

					case 1:
						for(int i = 0; i<hourSchedule.length; i++)
							feedback = feedback + "\n\t"  + hourSchedule[i] + "\t\t" + programName1[i] 
									+ "\t\t" + description1[i];
						break;
					case 2:
						for(int i = 0; i<hourSchedule.length; i++)
							feedback = feedback + "\n\t"  + hourSchedule[i] + "\t\t" + programName2[i] 
									+ "\t\t" + description2[i];
						break;
					case 3:
						for(int i = 0; i<hourSchedule.length; i++)
							feedback = feedback + "\n\t"  + hourSchedule[i] + "\t\t" + programName3[i] 
									+ "\t\t" + description3[i];
						break;
					case 4:
						for(int i = 0; i<hourSchedule.length; i++)
							feedback = feedback + "\n\t"  + hourSchedule[i] + "\t\t" + programName4[i] 
									+ "\t\t" + description4[i];
						break;
					}
					break;

				case HourSchedule:

					feedback = "\n Schedule for every channel for hour: " + read.getHour() + ":\n" 
							+ "\tCHANNEL:" + "\t" + "PROGRAM NAME:" + "\t" + "\tDESCRIPTION:"; 

					for (int i=0; i<hourSchedule.length; i++){
						if(hourSchedule[i].equals(read.getHour())){
							feedback = feedback + "\n\t" + "1.\t\t" + programName1[i] + "\t\t" + description1[i] 
									+ "\n\t" + "2.\t\t" + programName2[i] + "\t\t" + description2[i] 
											+ "\n\t" + "3.\t\t" + programName3[i] + "\t\t" + description3[i] 
													+ "\n\t" + "4.\t\t" + programName4[i] + "\t\t" + description4[i];
						}
					}
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
