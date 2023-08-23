

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
	/**
	 *
	 */

	/**
	 * Launch the application.
	 */
	CompiladorUtil util =  new CompiladorUtil();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					
					//Setting minimum size
					Dimension d = new Dimension();
					d.setSize(910, 600);
					
					frame.setMinimumSize(d);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		final TextArea textArea = new TextArea();
		textArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_BACK_SPACE) {
					util.textAreaLineCounter(textArea.getText());
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 33, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textArea, 31, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -64, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textArea, -27, SpringLayout.EAST, getContentPane());
		getContentPane().add(textArea);
		
	}
}
