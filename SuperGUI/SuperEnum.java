import java.awt.Image;

import javax.swing.ImageIcon;


public enum SuperEnum {
	GEAR("GEAR.png","\t\taddSequential(new AutoRotateCameraCommand());\n\t\taddSequential(new UltraSonicCourseCorrect(0));\n"), SHOOT("SHOOT.png","\t\taddSequential(AutoRotateCameraCommand());\n\t\taddSequential(new Shoot())\n");
	Image image;
	String codesnippet;
	SuperEnum(String filename, String code){
		image = new ImageIcon("SuperGUI/"+filename).getImage();
		codesnippet = code;
	}
}
