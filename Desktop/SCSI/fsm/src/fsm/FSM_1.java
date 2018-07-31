package fsm;

import lejos.nxt.*;
import lejos.util.*;
import lejos.nxt.comm.*;

public class FSM_1 {

    public static String state;
    public static LightSensor light = new LightSensor(SensorPort.S1);
    public static SoundSensor sound = new SoundSensor(SensorPort.S3); 
    public static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);

    public static void roll_note0() {
    	LCD.drawString(state + "               ",0,0);
    	if (light.getLightValue() > 55) {
        	state = "chirp&roll";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        } else if (us.getDistance() < 15) {
        	state = "avoid";
        } else state = "roll_note1";
    }
    public static void roll_note1() {
    	LCD.drawString(state + "               ",0,0);
        Motor.A.setSpeed(180);
        Motor.C.setSpeed(180); 
        Motor.A.forward();
        Motor.C.forward();
        Sound.playTone(440, 200);
        if (light.getLightValue() > 55) {
        	state = "chirp&roll";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        } else if (us.getDistance() < 15) {
        	state = "avoid";
        } else {
            Delay.msDelay(200);
        	state = "roll_note2";
        }
    }
    public static void roll_note2() {
    	LCD.drawString(state + "               ",0,0);
        Motor.A.setSpeed(180);
        Motor.C.setSpeed(180); 
        Motor.A.forward();
        Motor.C.forward();
        Sound.playTone(700, 200);
        if (light.getLightValue() > 55) {
        	state = "chirp&roll";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        } else if (us.getDistance() < 15) {
        	state = "avoid";
        } else {
            Delay.msDelay(200);
        	state = "roll_note3";
        }
    }
    public static void roll_note3() {
    	LCD.drawString(state + "               ",0,0);
        Motor.A.setSpeed(180);
        Motor.C.setSpeed(180); 
        Motor.A.forward();
        Motor.C.forward();
        Sound.playTone(300, 200);
        Delay.msDelay(200);
        if (light.getLightValue() > 55) {
        	state = "chirp&roll";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        } else if (us.getDistance() < 15) {
        	state = "avoid";
        } else {
            Delay.msDelay(200);
        	state = "roll_note0";
        }
    }
   
    public static void avoid() {
        LCD.drawString(state + "               ",0,0);
    	Motor.A.forward();
    	Motor.C.backward();
    	Delay.msDelay(1500);
    	Motor.C.forward();
    	if (light.getLightValue() > 55) {
        	state = "chirp&roll";
        } else if (light.getLightValue() < 55) {
        	state = "roll_note0";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        }
    }
    public static void stopping() {
    	LCD.drawString(state + "         ", 0, 0);
    	Motor.A.stop();
    	Motor.C.stop();
    	if (sound.readValue() > 50) {
        	state = "roll_note0";
        }
    }
    
    public static void chirp_n_roll()
    {
        LCD.drawString(state + "               ",0,0);
        Sound.playTone(700, 200);
        Motor.A.setSpeed(180);
        Motor.C.setSpeed(180); 
        Motor.A.forward();
        Motor.C.forward();
        if (light.getLightValue() < 55) {
        	state = "roll_note0";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        } else if (us.getDistance() < 15) {
        	state = "avoid";
        }
    }



    public static void main (String[] args) {
        state = "roll_note0";
        Delay.msDelay(500);
        while(true) {
        	if (state.equals("roll_note0")) {
        		roll_note0();
        	} else if (state.equals("roll_note1")) {
                roll_note1();
            }else if (state.equals("roll_note2")) {
                roll_note2();
            } else if (state.equals("roll_note3")) {
                roll_note3();
            } else if (state.equals("chirp&roll")) {
                chirp_n_roll();
            } else if (state.equals("stopping")) {
            	stopping();
            	Delay.msDelay(200);
            } else if (state.equals("avoid")) {
            	avoid();
            }
            else {
                LCD.drawString("Error: check ur code", 0, 0);
            }
        }
    }
}


/*public static void roll() {
    
    if (light.getLightValue() > 55) {
    	state = "chirp&roll";
    } else if (sound.readValue() > 50) {
    	state = "stopping";
    } else if (us.getDistance() < 15) {
    	state = "avoid";
    } else {
        Sound.playTone(700, 500);
        Delay.msDelay(500);
        if (light.getLightValue() > 55) {
    	state = "chirp&roll";
        } else if (sound.readValue() > 50) {
        	state = "stopping";
        } else if (us.getDistance() < 15) {
        	state = "avoid";
        } else {
	        Sound.playTone(300, 200);
	        Delay.msDelay(200);
        }
if (light.getLightValue() > 55) {
	state = "chirp&roll";
	 } else if (sound.readValue() > 50) {
		 state = "stopping";
	 } else if (us.getDistance() < 15) {
		 state = "avoid";
	 
	 }
}
}
}*/
// Transitions to other states


