import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import java.awt.TextArea;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 944, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JSplitPane splitPane = new JSplitPane();
		springLayout.putConstraint(SpringLayout.NORTH, splitPane, 90, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, splitPane, -47, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, splitPane, 36, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, splitPane, -10, SpringLayout.EAST, frame.getContentPane());
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane);
		
		TextArea textArea = new TextArea();
		textArea.setRows(20);
		textArea.setColumns(20);
		splitPane.setLeftComponent(textArea);
		
		TextArea textArea_1 = new TextArea();
		splitPane.setRightComponent(textArea_1);
		
		JToolBar toolBar = new JToolBar();
		springLayout.putConstraint(SpringLayout.NORTH, toolBar, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, toolBar, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, toolBar, -17, SpringLayout.NORTH, splitPane);
		springLayout.putConstraint(SpringLayout.EAST, toolBar, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(toolBar);
		
		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		toolBar.add(btnNewButton_2);
		
		JProgressBar progressBar = new JProgressBar();
		springLayout.putConstraint(SpringLayout.NORTH, progressBar, 6, SpringLayout.SOUTH, splitPane);
		springLayout.putConstraint(SpringLayout.WEST, progressBar, -900, SpringLayout.EAST, splitPane);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, progressBar, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(progressBar);
	}
}
