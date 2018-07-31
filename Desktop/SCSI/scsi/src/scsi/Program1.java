package scsi;

import lejos.nxt.*;
import lejos.util.*;

public class Program1 {
    public static void main(String[] args) {

        Motor.A.forward();
        Motor.B.forward();
        Delay.msDelay(500);


        Motor.A.backward();
        Motor.B.backward();
        Delay.msDelay(500);

        Motor.A.stop(true);
        Motor.B.stop(true);

        Motor.A.setSpeed(360);
        Motor.B.setSpeed(360);
        Motor.A.forward();
        Motor.B.backward();
        Delay.msDelay(3000);
        
        
        Motor.A.backward();
        Motor.B.forward();
        Delay.msDelay(3000);
        
        
        Motor.A.stop(true);
        Motor.B.stop(true);

    }
}