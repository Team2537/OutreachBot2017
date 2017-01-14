package shooter;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	private Talon BackLeftFly = new Talon(Ports.BLEFT_FLY);
	private Talon FrontLeftFly = new Talon(Ports.FLEFT_FLY);
	private Talon BackRightFly = new Talon(Ports.BRIGHT_FLY);
	private Talon FrontRightFly = new Talon(Ports.FRIGHT_FLY);
	public static final int SPEED = 1;
	public Ultrasonic ultron = 
	public ShooterSubsystem() {
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	public void setFlySpeed(Talon t,double speed){
		t.set(speed);
		
	}
	public void FlyOn(){
		
		setFlySpeed(BackLeftFly, SPEED);
		setFlySpeed(FrontLeftFly, SPEED);
		setFlySpeed(BackRightFly, SPEED);
		setFlySpeed(FrontRightFly, SPEED);
	}
	public void FlyOff(){
		setFlySpeed(BackLeftFly, 0);
		setFlySpeed(FrontLeftFly,0);
		setFlySpeed(BackRightFly,0);
		setFlySpeed(FrontRightFly,0);
	}

}
