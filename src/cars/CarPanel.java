package cars;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;

import javax.swing.Timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Car> CarList = new ArrayList<Car>();
	private ExecutorService  threadExecutor = Executors.newCachedThreadPool();

    
	public CarPanel(ArrayList<Car> c) {
		CarList = c;
		setSize(200, 400);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLUE));
		this.addMouseListener(new Mouse());
	    threadExecutor.execute(this); 
    }
	
    private void newCar (MouseEvent event){
    	Car car;
    	Point position = new Point(event.getPoint());
    	Point velocity = new Point(5,5);
    	Point acceleration = new Point(0,0);
    	
    	car = new Car(this,position,velocity,acceleration);
    	synchronized(CarList){
    		CarList.add(car);
    	}
        threadExecutor.execute( car );
    }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		synchronized(CarList){
		for (Car b: CarList){
			b.paint(g);
		}
		}
	}

    public void run(){
        while (true){

            repaint();

            //sleep while waiting to display the next frame of the animation
            try{
                Thread.sleep(40);  // wake up roughly 25 frames per second
            }
            catch ( InterruptedException exception )
            {
                System.out.printf("interrupt\n");
            }
        }
    }
    
    
    private class Mouse extends MouseAdapter
    {
    	/* A single click event can wait for the multiClickInterval to elapse,
         * and check this boolean to determine if it is the first click of a double click
         */
        Boolean doubleClick = false;
        private final Integer waitTime = (Integer) 300;
		@Override
        public void mousePressed( final MouseEvent event )
        {
            	if (event.getClickCount() >= 2) {
                    doubleClick = true;
                    System.out.println("acting on a double click");
                    newCar(event);
                } else {
                    Timer timer = new Timer(waitTime, new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            if (doubleClick) {

                                /* we are the first click of a double click */
                                doubleClick = false;
                            } else {

                                /* we waited for a second click that never came */
                                System.out.println("acting on a single click");
                                	newCar(event);
                            }
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }


