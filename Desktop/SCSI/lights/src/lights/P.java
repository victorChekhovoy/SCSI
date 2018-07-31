package lights;

import lejos.nxt.*;
import lejos.util.*;

public class P {
    public static void main(String[] args) {
    	int target = 58;
    	int pow = 25;
    	int adjust = 19;
    	double kp = 7.0; //4.0 works, 7.0 makes it jerk on straight lines and very sensitive
    	LightSensor light = new LightSensor(SensorPort.S1);
        NXTMotor motorA = new NXTMotor(MotorPort.A);
        NXTMotor motorB = new NXTMotor(MotorPort.B);
        motorA.setPower(pow);
        motorB.setPower(pow);
        while (true)
        {
        	int lightRead = light.getLightValue();
        	int error = lightRead - target;
        	int correct = (int)kp*error;
        	LCD.drawString("Error: " + error, 0, 0);
        	LCD.drawString("Correct: " + correct, 0, 1);
        	
        	motorA.setPower(pow - correct);
        	motorB.setPower(pow + correct);
        	
        	}
        }
        // Percentage ranging from 0 to 100
        
        
        // This next line waits for you to press a button before proceeding
        // further. It's here at the very end of the program so that the program
        // doesn't end instantly.
        
    
}