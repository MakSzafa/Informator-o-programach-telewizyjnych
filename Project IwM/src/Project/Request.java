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
}
