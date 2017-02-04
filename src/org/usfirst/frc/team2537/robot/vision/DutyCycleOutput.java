package org.usfirst.frc.team2537.robot.vision;

import edu.wpi.first.wpilibj.PIDOutput;

public class DutyCycleOutput implements PIDOutput {

	private double output = 0;
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.output = output;
	}
	
	public double get(){
		return output;
	}

}
