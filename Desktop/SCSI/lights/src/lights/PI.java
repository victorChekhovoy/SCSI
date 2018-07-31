package lights;

import lejos.nxt.*;
import lejos.util.*;

public class PI {
    public static void main(String[] args) {
    	int target = 58;
    	int pow = 25;
    	int adjust = 19;
    	double kp = 4.0;
    	int offset = 8;
    	double ki = 0.0001;
    	int errorSum = 0;
    	
    	LightSensor light = new LightSensor(SensorPort.S1);
        NXTMotor motorA = new NXTMotor(MotorPort.A);
        NXTMotor motorB = new NXTMotor(MotorPort.B);
        
        motorA.setPower(pow + offset);
        motorB.setPower(pow - offset);
        while (true)
        {
        	int lightRead = light.getLightValue();
        	int error = lightRead - target;
        	int correct = (int)kp*error;
        	LCD.drawString("Error: " + error, 0, 0);
        	LCD.drawString("Correct: " + correct, 0, 1);
        	errorSum += error;
        	motorA.setPower(pow + offset - correct - (int)(errorSum * ki));
        	motorB.setPower(pow + correct - offset + (int)(errorSum * ki));
        	
        	}
        }
        // Percentage ranging from 0 to 100
        
        
        // This next line waits for you to press a button before proceeding
        // further. It's here at the very end of the program so that the program
        // doesn't end instantly.
        
    
}