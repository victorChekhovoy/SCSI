package scsi;

import java.util.Random;

import lejos.nxt.*;
import lejos.util.Delay;

// Initial Q-learning program.
// by Dave Musicant

// Create a 2-d array of Q-values, initialize them to 0, and
// arbitrarily set values for two of them.

public class Qlearn {
    public static int reward;

    public static final int NUMSTATES = 3;
    public static final int NUMACTIONS = 3;
    public static final double alfa = 0.5;
    public static final int TURNLEFT = 0;
    public static final int TURNRIGHT = 1;
    public static final int FORWARD = 2;
    public static final int[] actionsas = {TURNLEFT, TURNRIGHT, FORWARD};
    public static final int MINULTRA = 10;
    public static final int MAXULTRA = 15;
    public static int state = 0;
    public static double oldq = 0;
    public static final int TOOCLOSE = 0;
    public static final int GOOD = 1;
    public static final int TOOFAR = 2;
    public static final int[] statesas= {TOOCLOSE, GOOD, TOOFAR};
    public static Random randGen = new Random();
    public static UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S1);
    public static final String[] states = {"TC","GE","TF"};

    // Display array of Q-values to the screen.
    // Since the bottom-left of the screen is the "0" location,
    // it is easier to to display everything from the bottom up
    // than from the top down.
    public static void display(double qvalues[][])
    {
        LCD.clear();
        for (int i=0; i < NUMSTATES; i++)
            for (int j=0; j < NUMACTIONS; j++) {
                LCD.drawString(states[i],0,i);
                LCD.drawInt((int)(qvalues[i][j]*100),3,3+j*5,i);
            }
        LCD.drawString("    TL   TR   GF",0,3);
        LCD.drawString("Q-vals are x100", 0, 4);
        LCD.drawString("to save space", 0, 5);
    }




    public static void main(String[] args) {
        double qvalues[][] = new double[NUMSTATES][NUMACTIONS];
        for (int i=0; i < NUMSTATES; i++)
            for (int j=0; j < NUMACTIONS; j++)
                qvalues[i][j] = 0;
        // These two lines of code are just a sample of how you might assign
        // values to the qvalues array. The values 1.8 and 0.7 are meaningless,
        // it's just an example
        qvalues[GOOD][FORWARD] = reward;
        //qvalues[GOOD][FORWARD] = 0.7;

        display(qvalues);
        while(true)
        {
        	int action = randGen.nextInt(3);
        	if (action == 0) turnLeft();
        	if (action == 1) turnRight();
        	if (action == 2) goForward();
        	setState();
        	qvalues[state][action] = qvalues[state][action] + alfa*(oldq - qvalues[state][action]);
        	oldq = qvalues[state][action];
            LCD.drawString("Distance:",0,7);
            LCD.drawInt(sonic.getDistance(),3,10,7);
        }

    }
    public static void setState() {
    	int distance = sonic.getDistance();
    	if (distance > 30) {
    		state = 2;
    	} else if (distance >= 15 && distance <= 30) {
    		state = 1;
    	} else if (distance < 15) {
    		state = 0;
    	}
    }
    public static void turnRight() {
    	
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
    	
    }
    
    public static void turnLeft() {
    	
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
    	
    }
    
    public static void goForward() {
    	
    	Motor.A.forward();
    	Motor.C.forward();
    	
    }
    
}