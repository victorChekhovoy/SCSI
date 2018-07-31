package lights;

import lejos.nxt.*;
import lejos.util.*;

public class PID {
    public static void main(String[] args) {
    	int target = 58;
    	int pow = 35;
    	int adjust = 19;  	
    	int offset = 0;
    	int errorSum = 0;
    	
    	double kp = 7.5;
    	double ki = 0.0001;
    	double kd = 0.5;
    	
    	/* working k values
    	 * kp ki kd = 6.0, 0.0001, 0.5

    
    	*/
    	LightSensor light = new LightSensor(SensorPort.S1);
        NXTMotor motorA = new NXTMotor(MotorPort.A);
        NXTMotor motorB = new NXTMotor(MotorPort.B);
        
        motorA.setPower(pow + offset);
        motorB.setPower(pow - offset);
        int oldError = 0;
        while (true)
        {
        	int lightRead = light.getLightValue();
        	int error = lightRead - target;
        	int der = error - oldError;
        	int correct = (int)kp*error;
        	LCD.drawString("Error: " + error, 0, 0);
        	//LCD.drawString("Correct: " + correct, 0, 1);
        	errorSum += error;
        	int intCor = (int)(errorSum * ki);
        	int derCor = (int)(der*kd);
        	motorA.setPower(pow + offset - correct - intCor - derCor);
        	motorB.setPower(pow + correct - offset + intCor + derCor);
        	oldError = error;
        	}
        }
        // Percentage ranging from 0 to 100
        
        
        // This next line waits for you to press a button before proceeding
        // further. It's here at the very end of the program so that the program
        // doesn't end instantly.
        
    
}