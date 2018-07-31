package scsi;

import lejos.nxt.*;
import lejos.util.*;


// A sample program using the touch sensors.
// by Dave Musicant

public class bumpers
{
    public static void main(String[] args)
    {
        // Initial settings
        int speed = 180;
        TouchSensor sensor1 = new TouchSensor(SensorPort.S1);
        TouchSensor sensor4 = new TouchSensor(SensorPort.S4);
        lejos.util.Stopwatch t = new lejos.util.Stopwatch();
        Motor.A.setSpeed(speed);
        Motor.B.setSpeed(speed);
        Motor.A.forward();
        Motor.B.forward();
        int i = 0;
        // Main loop
        while (true) {


            LCD.drawString("Sensor 1: " + sensor1.isPressed() + " ",0,0);
            LCD.drawString("Sensor 4: " + sensor4.isPressed() + " ",0,1);

            if (sensor1.isPressed() && !sensor4.isPressed())
            {
            	if(t.elapsed() >= 100) {
            		t.reset();
            	}
            	else {
            		speed+=50;
            		Delay.msDelay(101);
            		continue;
            	}
            
                LCD.drawString("Turning right.          ",0,2);
                
                Motor.B.backward();
            }
            else if (sensor4.isPressed() && !sensor1.isPressed())
            {
            	if(t.elapsed() >= 100) {
            		t.reset();
            	}
            	else {
            		speed-=50;
            		Delay.msDelay(101);
            		continue;
            	}
                LCD.drawString("Turning left.           ",0,2);
                
                Motor.A.backward();
            }
            else if (sensor4.isPressed() && sensor1.isPressed()) {
            	Motor.A.setSpeed(speed);
            	Motor.B.setSpeed(speed);
            	
            	Motor.A.forward();
            	Motor.B.forward();
            	i++;
                LCD.drawString(""+i, 0,2);
            	
            }
            else
            {
                LCD.drawString("Going straight.         ",0,2);
            
            Delay.msDelay(100);
            Motor.A.stop(true);
        	Motor.B.stop(true);
            	
        }
    }
    }
}