package scsi;

import lejos.nxt.*;
import lejos.util.Delay;

public class Ultrasonic {

	public static String state = "good";
	public static int distance;
	
    public static void main(String[] args) {
    	
        UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S1);
        
        Motor.A.setSpeed(180);
        Motor.C.setSpeed(180);
        
        while(true)
        {
        	distance = sonic.getDistance();
        	
        	Motor.A.forward();
        	Motor.C.forward();
        	
        	if (state.equals("good")) {
        		good();
        	} else if (state.equals("tooClose")) {
        		tooClose();
        	} else if (state.equals("tooFar")) {
        		tooFar();
        	} else {
        		LCD.drawString("error - check ur code", 0, 0);
        	}
        	Sound.playTone(700, 100);
        	//Delay.msDelay(50);
         //   LCD.drawString("Distance:",0,6);
           // LCD.drawInt(sonic.getDistance(),3,10,6);
        }

    }
    
    public static void tooClose() {
    	LCD.drawString(state + distance + "          ", 0, 0);
    	
    	Motor.C.backward();
    	Motor.A.forward();
    	Delay.msDelay(400);
    	Motor.C.forward();
    	Motor.A.forward();
    	Delay.msDelay(800);
    	Motor.A.backward();
    	Motor.C.forward();
    	Delay.msDelay(400);
    	Motor.A.forward();
    	Motor.C.forward();
    	
    	//Change states
    	if (distance > 30) {
    		state = "tooFar";
    	} else if (distance >= 15 && distance <= 30) {
    		state = "good";
    	}
    }
    
    public static void tooFar() {
    	LCD.drawString(state + distance + "          ", 0, 0);
    	
    	Motor.A.backward();
    	Motor.C.forward();
    	Delay.msDelay(400);
    	Motor.A.forward();
    	Motor.C.forward();
    	Delay.msDelay(800);
    	Motor.C.backward();
    	Motor.A.forward();
    	Delay.msDelay(400);
    	Motor.C.forward();
    	Motor.A.forward();
    	
    	
    	//Change states
    	if (distance < 15) {
    		state = "tooClose";
    	} else if (distance >= 15 && distance <= 30) {
    		state = "good";
    	}
    }
   
    public static void good() {
    	LCD.drawString(state + distance + "          ", 0, 0);
    	
    	Motor.A.forward();
        Motor.C.forward();
    	
    	//Change states
    	if (distance > 30) {
    		state = "tooFar";
    	} else if (distance < 15) {
    		state = "tooClose";
    	}
    }
}