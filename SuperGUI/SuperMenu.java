import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class SuperMenu extends JPopupMenu {
	private JMenuItem gearSelect;
	private JMenuItem shootSelect;
	
	public SuperMenu(ActionListener l){
		gearSelect = new JMenuItem("GEAR");
		gearSelect.addActionListener(l);
		shootSelect = new JMenuItem("SHOOT");
		shootSelect.addActionListener(l);
		add(gearSelect);
		add(shootSelect);
	}
}
