package pathFinding;

import java.sql.Time;
import java.util.Vector;

import dataStructures.Node;
import dataStructures.Reservation;

public class TimePosReservations {
	
	Vector<Reservation> reservations = new Vector<Reservation>();

	public TimePosReservations(){
		
	}
	
	public void addReservation(int time, Node node) {
		reservations.add(new Reservation(time, node));
	}
	
	public void removeFirstReservation(){
		reservations.remove(0);
	}
	
	/*public boolean isReserved(int time, Node node){
		
	}*/
}
