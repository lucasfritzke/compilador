import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;

public class Main {

    private JFrame frame;

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

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 949, 609);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JSplitPane splitPane = new JSplitPane();
        springLayout.putConstraint(SpringLayout.NORTH, splitPane, 90, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, splitPane, -47, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, splitPane, -10, SpringLayout.EAST, frame.getContentPane());
        splitPane.setDividerLocation(300); // Defina uma altura inicial para o JSplitPane
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        frame.getContentPane().add(splitPane);

        JTextArea textArea = new JTextArea(15, 30); // Defina mais linhas e colunas aqui
        textArea.setBorder(new NumberedBorder());
        
        JScrollPane textScrollPane = new JScrollPane(textArea); // Crie um JScrollPane para o JTextArea
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);// Mantenha a barra de rolagem sempre visível
        splitPane.setLeftComponent(textScrollPane); // Adicione o JScrollPane ao SplitPane

        
        
        JTextArea textArea_1 = new JTextArea(10,20);
        textArea_1.setEditable(false);
        JScrollPane textScrollPane_1 = new JScrollPane(textArea_1);
        textScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        splitPane.setRightComponent(textScrollPane_1);
        ImageIcon i = new ImageIcon(Main.class.getResource("/icons/novo.png"));
        

        JProgressBar progressBar = new JProgressBar();
        springLayout.putConstraint(SpringLayout.NORTH, progressBar, 6, SpringLayout.SOUTH, splitPane);
        springLayout.putConstraint(SpringLayout.WEST, progressBar, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, progressBar, -10, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, progressBar, -10, SpringLayout.EAST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, splitPane, 0, SpringLayout.WEST, progressBar);
        frame.getContentPane().add(progressBar);
        
        JPanel panel = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel, -80, SpringLayout.NORTH, splitPane);
        springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, splitPane);
        springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, splitPane);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, 910, 74);
        panel.add(toolBar);
        
        JButton btnNewButton = new JButton("Novo [Crtl-N]");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);
        btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
        btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/icons/Novo4.png")));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
       
        toolBar.add(btnNewButton);
    }
}
