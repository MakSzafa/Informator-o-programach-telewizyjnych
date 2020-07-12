package Project;

public class Channel {
	
			// atrybuty klasy
			protected int channelNr;
			protected String hour;
			
			// konstruktor klasy
			public Channel() {
				channelNr = 1;
				hour = "15:00";
			}
			public Channel(int channelNr, String hour) {
				this.channelNr = channelNr;
				this.hour = hour;
			}
			
			// wypelnienie harmonogramu godzinowego
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
