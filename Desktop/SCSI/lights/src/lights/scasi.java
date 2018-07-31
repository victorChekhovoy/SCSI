package lights;

import lejos.nxt.*;
import lejos.util.*;

public class scasi
{
    public static void main(String[] args)
    {
        LightSensor light = new LightSensor(SensorPort.S1);
        while (true)
        {
            int value = light.getLightValue();
            LCD.drawInt(value, 4, 0, 0);
        }

    }
}