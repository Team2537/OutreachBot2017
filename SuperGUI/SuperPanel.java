import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Panel
 *
 * @author Arden Zhang
 *
 */
public class SuperPanel extends JPanel implements KeyListener,
		MouseMotionListener, MouseListener, MouseWheelListener {

	private static final int mouseSize = 8; // pixels
	private static final int animationGap = 3; // ms
	private static final int snapKey = KeyEvent.VK_SPACE;
	private SuperEnum mode = SuperEnum.GEAR;
	private Image field;
	private boolean snap;
	private Point mousePos;
	private int frame;
	private SuperBot bot;
	private int botTransparency;
	private JFrame jframe;

	public SuperPanel() {
		field = new ImageIcon("SuperGUI/FIELD.jpg").getImage();
		SuperEnum[] enumVals = SuperEnum.values();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		setPreferredSize(new Dimension(
				(int) (SuperGUI.FIELD_LENGTH * SuperGUI.SCALE),
				(int) (SuperGUI.FIELD_WIDTH * SuperGUI.SCALE)));
		snap = false;
		mousePos = new Point(0, 0);
		frame = 0;
		botTransparency = 255;
		jframe = new JFrame();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(field, 0, 0, null);
		g.drawImage(mode.image, 0, 0,50,50, null);

		if (bot != null)
			bot.draw(g, botTransparency);

		Color line = new Color(0, 255, 255);
		Color xFill = new Color(255, 255, 255, 40);
		Color yFill = new Color(255, 255, 255, 40);

		for (int i = 0; i < frame; i++) {
			g.setColor(line);
			g.drawLine(i * SuperGUI.SCALE, 0, i * SuperGUI.SCALE, getHeight());
			g.setColor(xFill);
			g.fillRect(i * SuperGUI.SCALE, 0, SuperGUI.SCALE, getHeight());
		}

		for (int j = 0; j < frame / 2; j++) {
			g.setColor(line);
			g.drawLine(0, j * SuperGUI.SCALE, getWidth(), j * SuperGUI.SCALE);
			g.setColor(yFill);
			g.fillRect(0, j * SuperGUI.SCALE, getWidth(), SuperGUI.SCALE);
		}

		if (snap) {
			if (frame <= SuperGUI.FIELD_LENGTH)
				frame++;
		} else if (frame >= 0)
			frame--;

		g.setColor(new Color(255, 255, 0));
		g.drawOval(mousePos.x - mouseSize, mousePos.y - mouseSize,
				mouseSize * 2, mouseSize * 2);

		if (frame >= 0 && frame <= SuperGUI.FIELD_LENGTH + 1) {
			try {
				Thread.sleep(animationGap);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	private void quit() {
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == snapKey)
			snap = !snap;
		if (k.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("Course================" + bot.getNumBots());
			String s = (String) JOptionPane.showInputDialog(jframe,
					"Complete the sentence:\n", "File Name",
					JOptionPane.PLAIN_MESSAGE, null, null, "");
			File fl = new File("src\\org\\usfirst\\frc\\team2537\\maps\\" + s
					+ ".java");
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fl));
				writer.flush();
				writer.write("package org.usfirst.frc.team2537.maps;\n\n");
				writer.write("import edu.wpi.first.wpilibj.command.CommandGroup;\n");
				writer.write("import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;\n");
				writer.write("import org.usfirst.frc.team2537.robot.auto.CourseCorrect;\n\n");
				writer.write("public class " + s + " extends CommandGroup {\n");
				writer.write("\tpublic " + s + "(){\n");
				SuperPoint.printCourse(bot, SuperGUI.ROBOT_START_ANGLE, writer);
				writer.write("\t}\n");
				writer.write("}\n");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_M) {
			SuperEnum[] enumVals = SuperEnum.values();

			for (int i = 0; i < enumVals.length; i++) {
				if(enumVals[i] == mode){
					mode = enumVals[(i+1)%(enumVals.length)];
					break;
				}
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_ESCAPE)
			quit();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent k) {
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		mousePos.x = m.getX();
		mousePos.y = m.getY();
		if (snap)
			mousePos = snap(mousePos);
		repaint();
	}

	private Point snap(Point p) {
		int x = p.x;
		int y = p.y;

		if (x % SuperGUI.SCALE > SuperGUI.SCALE / 2)
			x += SuperGUI.SCALE - x % SuperGUI.SCALE;
		else
			x -= x % SuperGUI.SCALE;

		if (y % SuperGUI.SCALE > SuperGUI.SCALE / 2)
			y += SuperGUI.SCALE - y % SuperGUI.SCALE;
		else
			y -= y % SuperGUI.SCALE;

		return new Point(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		if (SwingUtilities.isRightMouseButton(m)) {
			if (bot != null){
				if (bot.contains(mousePos) == -1){
					bot.point(mousePos); // point
				}
				else{
					System.out.println(mode+" "+ bot.contains(mousePos));
					bot.setMode(mode,bot.contains(mousePos));
				}
			}
				
		} else if (bot == null)
			bot = new SuperBot(mousePos);
		else
			bot.add(mousePos);
		repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		botTransparency -= 10 * m.getPreciseWheelRotation();
		if (botTransparency > 255)
			botTransparency = 255;
		if (botTransparency < 0)
			botTransparency = 0;
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent k) {
	}

	@Override
	public void mousePressed(MouseEvent m) {
	}

	@Override
	public void mouseReleased(MouseEvent m) {
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		mouseMoved(m);
	}

	@Override
	public void mouseEntered(MouseEvent m) {
	}

	@Override
	public void mouseExited(MouseEvent m) {
	}

}
