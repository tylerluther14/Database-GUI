import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DisplayTwo 
{
	public DisplayTwo()
	{
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new LayoutManager());
		
		
	}

	public static void main (String[] args)
	{
		DisplayTwo d = new DisplayTwo();
	}
}
