package cameras;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Cameras {
	public Cameras() {
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam", 0);
			camera.setResolution(640, 480);

			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Cogsworth", 640, 480);

			Mat source = new Mat();
			Mat output = new Mat();

			while (!Thread.interrupted()) {
				cvSink.grabFrame(source);
				output = source;
				if (Robot.driveSys.getUltron() < Robot.driveSys.ultronFarRange
						&& !(Robot.driveSys.getUltron() < Robot.driveSys.ultronCloseRange)) {
					Core.add(source, new Scalar(0, 100, 0), output);
				} else if (Robot.driveSys.getUltron() < Robot.driveSys.ultronCloseRange) {
					Core.add(source, new Scalar(0, 0, 100), output);
				}
				outputStream.putFrame(output);
			}
		}).start();
	}
}
