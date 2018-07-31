package scsi;

import lejos.nxt.*;
import lejos.util.*;
public class boxx {
 public static void main(String[] args) {
	 int speed = 180;
     TouchSensor sensor1 = new TouchSensor(SensorPort.S1);
     lejos.util.Stopwatch t = new lejos.util.Stopwatch();
     Motor.A.setSpeed(speed);
     Motor.B.setSpeed(speed);
     Motor.A.resetTachoCount();
     Motor.A.flt();
     Motor.B.resetTachoCount();
     Motor.B.flt();
     //int a = 0;
    // int b;
     for(int i = 0; i < 2; i += 0) {
    	 Motor.A.forward();
    	 Motor.B.forward();
    	 if(!sensor1.isPressed()) {
    		 Delay.msDelay(390);
    		 Motor.A.stop();
    		 Motor.B.stop();
	    	 LCD.drawInt(Motor.A.getTachoCount(),4,0,0);
	         //LCD.drawInt(Motor.B.getTachoCount(),4,10,0);
	         Motor.A.backward();
	         Motor.B.forward();
	         Delay.msDelay(760);
	         i++;
	         Motor.A.resetTachoCount();
	         Motor.A.flt();
	         Motor.B.resetTachoCount();
	         Motor.B.flt();
     }
     } 
		 Motor.A.stop();
		 Motor.B.stop();
 }
}
