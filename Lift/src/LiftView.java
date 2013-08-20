import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class LiftView extends JPanel {
	
	private LiftController controller;
	
	private int cabWidth;
	private int floorHeight;
	private int position;
	private int cabHeight;
	
	private JButton floors[];
	
	public LiftView() {
		this.cabWidth = 100;
		this.floorHeight = 200;
		this.cabHeight = 150;
		this.position = 0;
		
		this.setLayout(null);
		
		for(int i = 0 ; i < 3 ; i++) {
			this.floors = new JButton[3];
			this.floors[i] = new JButton();
			floors[i].setBounds(this.cabWidth+30, (5-2*i)*this.floorHeight/2, 50, 30);
			this.add(floors[i]);
		}
		
	}
	
	public void setController(LiftController controller) {
		this.controller = controller;
	}
	
	public LiftController getController() {
		return this.controller;
	}
	
	public int getFloorHeight() {
		return this.floorHeight;
	}
	
	public int getWidth() {
		return this.cabWidth;
	}
	
	public void up() {
		//this.floors[this.controller.getCurrentFloor()].setSelected(false);
		this.position--;
		if(this.position % this.floorHeight == 0) {
			this.controller.setCurrentFloor(this.position / this.floorHeight);
			this.controller.floorReached();
			//this.floors[this.controller.getCurrentFloor()].setSelected(true);
		}
	}
	
	public void down() {
		//this.floors[this.controller.getCurrentFloor()].setSelected(false);
		this.position++;
		if(this.position % this.floorHeight == 0) {
			this.controller.setCurrentFloor(this.position / this.floorHeight);
			this.controller.floorReached();
			//this.floors[this.controller.getCurrentFloor()].setSelected(true);
		}
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.drawLine(10, 10+3*this.floorHeight, 15+this.cabWidth, 10+3*this.floorHeight);
		g.drawLine(10, 10+2*this.floorHeight, 15+this.cabWidth, 10+2*this.floorHeight);
		g.drawLine(10, 10+this.floorHeight, 15+this.cabWidth, 10+this.floorHeight);
		g.drawLine(10, 10, 15+this.cabWidth, 10);
		
		g.setColor(Color.GRAY);
		g.fillRect(10, 10+3*this.floorHeight-this.position-this.cabHeight, this.cabWidth/2, this.cabHeight);
		g.fillRect(20+(this.cabWidth-10)/2, 10+3*this.floorHeight-this.position-this.cabHeight, this.cabWidth/2, this.cabHeight);
		
		g.setColor(Color.BLACK);
		g.drawLine(10, 10+3*this.floorHeight, 10, 10);
		g.drawLine(15+this.cabWidth, 10+3*this.floorHeight, 15+this.cabWidth, 10);
	}

}
