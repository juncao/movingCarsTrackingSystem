package cars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

public class Car implements Runnable, Serializable{
	private static final long serialVersionUID = 1L;

	//public final static Random random = new Random();
	
	final static int SIZE = 10;
	final static int MAX_SPEED = 25;
	
	private Boolean paused = false;
	private Point position;
	private Point velocity;
	private Point acceleration;
    
    private CarPanel myApp;
    public static enum BounceSide {LEFT,RIGHT}


    public Car (CarPanel myApp, Point position, Point velocity, Point acceleration){
    	this.myApp = myApp;
    	this.position = position;
    	this.velocity = velocity;
    	this.acceleration = acceleration;
    }
    public void paint(Graphics g){
        g.setColor(Color.blue);
	    g.drawOval((int) position.getX(), (int) position.getY(), SIZE, SIZE);
        g.setColor(Color.red);
        g.fillOval((int) position.getX()+1, (int) position.getY()+1, SIZE-2, SIZE-2); 

    }
    
    public void setApp (CarPanel app){
         myApp = app;
    }
    public void setDX (int dx){
        velocity.setLocation(dx,velocity.getY());
    }
    public int getDX (){
        return((int)velocity.getX());
    }
    public void setXPos (int xPos){
        position.setLocation(xPos,position.getY());
    }
    public int getXPos (){
        return((int)position.getX());
    }
    public Point getPosition(){
    	return position;
    }
    public void pause(){
    	paused = true;
    }
    public void setPanel(CarPanel c){
    	myApp = c;
    }
    public CarPanel getPanel(){
    	return myApp;
    }
    public void run(){

        while (true){

            // check for bounce and make the ball bounce if necessary
            //
            if (position.getX() < 0 && velocity.getX() < 0){
                //bounce off the left wall -- application handles LEFT bounces
                //myApp.bounce(this, BounceSide.LEFT);
            	velocity.setLocation(-velocity.getX(),velocity.getY());
                acceleration.setLocation(-acceleration.getX(),acceleration.getY());
                //return;  // this thread is done
            }
            if (position.getY() < 0 && velocity.getY() < 0){
                //bounce off the top wall
                velocity.setLocation(velocity.getX(),-velocity.getY());
                acceleration.setLocation(acceleration.getX(),-acceleration.getY());
               // velocity.translate((int)acceleration.getX(),(int)acceleration.getY());
            }
            if (position.getX() > myApp.getWidth() - SIZE && velocity.getX() > 0){
                //bounce off the right wall -- application handles RIGHT bounces
                //myApp.bounce(this, BounceSide.RIGHT);
                //return;  // this thread is done
            	velocity.setLocation(-velocity.getX(),velocity.getY());
            	acceleration.setLocation(-acceleration.getX(),acceleration.getY());
            	velocity.translate((int)acceleration.getX(),(int)acceleration.getY());
            }
                
            if (position.getY() > myApp.getHeight() - SIZE && velocity.getY() > 0){
                //bounce off the bottom wall
            	velocity.setLocation(velocity.getX(),-velocity.getY());
            	acceleration.setLocation(acceleration.getX(),-acceleration.getY());
            	velocity.translate((int)acceleration.getX(),(int)acceleration.getY());
            }

            //make the ball move
            position.translate((int)velocity.getX(),(int)velocity.getY());
            
            //acceleration
            //velocity.translate((int)acceleration.getX(),(int)acceleration.getY());

            //sleep while waiting to update the next frame of the animation
            try{
            	if (paused){
            		paused = false;
            		Thread.sleep(5000); // pause for 5 seconds
            	}else{
                    Thread.sleep(40);  // wake up roughly 25 frames per second
            	}
            }
            catch ( InterruptedException exception )
            {
                exception.printStackTrace();
            }
        }
    }

}
