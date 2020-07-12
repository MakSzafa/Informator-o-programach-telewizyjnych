package Project;

import java.io.Serializable;

public class Request implements Serializable{

	public enum Type {
		HourChannelSchedule, ChannelSchedule, HourSchedule
	}

	// atrybuty klasy
	public int channelNr;
	public String hour;
	public Type type; 
   	
	// cos sros
	String hourSchedule[];
	String programName[];
	String description[];
	String schedule[][];
	

	// konstruktor klasy
	Request() {
		channelNr = 1;
		hour = "15:00";
		type = Type.HourChannelSchedule;
	}

	Request(int channelNr, String hour, Type type){
		this.channelNr = channelNr;
		this.hour = hour;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String toString() {

		String allValues;

		allValues = "\nChannel number: " + channelNr + "\nHour: " + hour + "\nType: " + type;

		return allValues;
	}

	//wypelnienie harmonogramu godzinowego
	public String [] Hour() {

		switch (channelNr) {

		case 1:
			String hour1[] = {"6:00", "7:00", "6:00", "6:00", "6:00", "6:00",
					"6:00", "6:00", "6:00", "6:00", "6:00", "6:00", "6:00"};
			return hour1;

		case 2:
			String hour2[] = {"6:00", "7:00", "6:00", "6:00", "6:00", "6:00",
					"6:00", "6:00", "6:00", "8:00", "6:00", "6:00", "6:00"};
			return hour2;

		case 3:
			String hour3[] = {"6:00", "7:00", "6:00", "6:00", "6:00", "6:00",
					"6:00", "6:00", "6:00", "6:00", "6:00", "6:00", "6:00"};
			return hour3;

		case 4:
			String hour4[] = {"6:00", "7:00", "6:00", "6:00", "6:00", "6:00",
					"6:00", "6:00", "6:00", "6:00", "6:00", "6:00", "6:00"};
			return hour4;

		default:
			String wrongChannelNr[] = {"You have chosen wrong channel number"};
			return wrongChannelNr;
		}
	}
}
