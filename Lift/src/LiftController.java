import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;


public class LiftController {
	
	private LiftView view;
	
	private int currentFloor;
	private LinkedList<Integer> destFloors;
	private int nextFloor;
	
	private Timer timer;
	 
	
	public LiftController(LiftView v) {
		this.currentFloor = 0;
		this.nextFloor = 2;
		this.destFloors = new LinkedList<Integer>();
		
		this.view = v;
		this.view.setController(this);
		this.timer = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentFloor < nextFloor) view.down();
				else if(currentFloor > nextFloor) view.up();
				view.repaint();
			}
			
		});
		this.timer.start();
	}
	
	public void setCurrentFloor(int floor) {
		this.currentFloor = floor;
		if(this.currentFloor == this.nextFloor)
			if(!this.destFloors.isEmpty()) this.nextFloor = this.destFloors.poll();
	}
	
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	public void floorReached() {
		if(this.currentFloor == this.nextFloor) {
			if(!this.destFloors.isEmpty()) this.currentFloor = ((Integer) this.destFloors.poll()).intValue();		
		}		
	}
	
	public void addFloor(int floor) {
		if(this.currentFloor == this.nextFloor) this.nextFloor = floor;
		else this.destFloors.add(new Integer(floor));
	}

}
