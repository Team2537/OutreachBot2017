package org.usfirst.frc.team2537.robot.vision;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PWMSubsystem extends Subsystem {
    private static Counter count;
    final static private double frequency = 191.9;
    
    public PWMSubsystem(){
    	count = new Counter(Ports.RASP_PI);
        count.setSemiPeriodMode(true);
    }
   
    public double getDutyCycle() {
        double dutyCycle = count.getPeriod()*frequency;
        System.out.println("DUTY CYCLE: " + dutyCycle + "		PERIOD: " + count.getPeriod());
        if(dutyCycle > 1) dutyCycle = 1;
        return dutyCycle;
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here
    }
}