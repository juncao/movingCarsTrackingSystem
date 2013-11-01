package cars;

import java.awt.*;

import javax.swing.JFrame;

import java.util.ArrayList;


public class MovingCars extends JFrame{

	private static final long serialVersionUID = 1L;
	
	ArrayList<Car> aCarList = new ArrayList<Car>();
	ArrayList<Car> bCarList = new ArrayList<Car>();
	ArrayList<Car> cCarList = new ArrayList<Car>();
	public CarPanel aPanel = new CarPanel(aCarList);
	public CarPanel bPanel = new CarPanel(bCarList);
	public CarPanel cPanel = new CarPanel(cCarList);
	
	 public static void main(String[] args) {
	        new MovingCars();
	    }
	 
	 public MovingCars() {
	        super("Bouncing Balls");
	        setSize(600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new GridLayout(1, 3));

	        add(aPanel);
	        add(bPanel);
	        add(cPanel);
	        setVisible(true);
	       
	        //addMouseListener(new MousePanel());
	    }
	 
	 
}
