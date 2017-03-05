package org.usfirst.frc.team2537.robot.vision;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PISubsystem extends Subsystem {
	public static final boolean PI_MOUNTED_RIGHT_SIDE_UP = false;
    private static final Counter count = new Counter(Ports.RASP_PI);
    final double frequency = 191.9;
    
    public PISubsystem(){
        count.setSemiPeriodMode(true);
    }
   
    public double getDutyCycle() {
        double dutyCycle = count.getPeriod()*frequency;
        if(dutyCycle > 1) dutyCycle = 1;
        return (PI_MOUNTED_RIGHT_SIDE_UP) ? dutyCycle : 1 - dutyCycle;
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here
    }
}
