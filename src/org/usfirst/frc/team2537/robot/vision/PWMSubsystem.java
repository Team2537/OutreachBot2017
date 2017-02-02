package org.usfirst.frc.team2537.robot.vision;
 
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PWMSubsystem extends Subsystem {
    private static final Counter count = new Counter(5);
    final double frequency = 191.9;
    
    public PWMSubsystem(){
        count.setSemiPeriodMode(true);
    }
   
    public double getDutyCycle() {
        double dutyCycle = count.getPeriod()*frequency;
        return dutyCycle;
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        
    }
}