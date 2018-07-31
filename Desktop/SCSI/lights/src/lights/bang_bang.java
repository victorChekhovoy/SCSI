package lights;

import lejos.nxt.*;
import lejos.util.*;

public class bang_bang {
    public static void main(String[] args) {
    	int pow = 20;
    	int adjust = 19;
    	LightSensor light = new LightSensor(SensorPort.S1);
        NXTMotor motorA = new NXTMotor(MotorPort.A);
        NXTMotor motorB = new NXTMotor(MotorPort.B);
        motorA.setPower(pow);
        motorB.setPower(pow);
        while (true)
        {
        	int lightRead = light.getLightValue();
        	if (lightRead > 47 && lightRead < 54)
        	{
        		motorA.setPower(pow - adjust);
        		motorB.setPower(pow + adjust);
        	}
        	else if (lightRead > 63)
        	{
        		motorA.setPower(pow + adjust);
        		motorB.setPower(pow - adjust);
        	}
        	else {
        		motorA.setPower(pow);  
        		motorB.setPower(pow);
        	}
        }
        // Percentage ranging from 0 to 100
        
        
        // This next line waits for you to press a button before proceeding
        // further. It's here at the very end of the program so that the program
        // doesn't end instantly.
        
    }
}