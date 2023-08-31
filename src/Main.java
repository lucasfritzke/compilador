import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Util.CompiladorUtil;
import Util.KeyHandler;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main{

    private JFrame frame;
    private CompiladorUtil util = new CompiladorUtil();
    private KeyHandler keyH = new KeyHandler(util);
    

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
        frame.setBounds(100, 100, 955, 623);
        frame.setMinimumSize(new Dimension(910, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JSplitPane splitPane = new JSplitPane();
        springLayout.putConstraint(SpringLayout.NORTH, splitPane, 90, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, splitPane, -47, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, splitPane, -10, SpringLayout.EAST, frame.getContentPane());
        splitPane.setDividerLocation(300); 
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        frame.getContentPane().add(splitPane);

        JTextArea textArea = new JTextArea(15, 30); 
        textArea.setName("CodeBlock");
        textArea.addKeyListener(keyH);
        util.addComponent(textArea);
        textArea.setBorder(new NumberedBorder());
        
        JScrollPane textScrollPane = new JScrollPane(textArea); 
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        splitPane.setLeftComponent(textScrollPane); 

        
        
        JTextArea textArea_1 = new JTextArea(10,20);
        textArea_1.setEditable(false);
        textArea_1.setName("MessageBlock");
        util.addComponent(textArea_1);
        JScrollPane textScrollPane_1 = new JScrollPane(textArea_1);
        textScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        splitPane.setRightComponent(textScrollPane_1);
        ImageIcon i = new ImageIcon(Main.class.getResource("/icons/novo.png"));
        

        JTextField progressBar = new JTextField();
        progressBar.setName("StatusBar");
        progressBar.setEditable(false);
        util.addComponent(progressBar);
        springLayout.putConstraint(SpringLayout.NORTH, progressBar, 6, SpringLayout.SOUTH, splitPane);
        springLayout.putConstraint(SpringLayout.WEST, progressBar, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, progressBar, -10, SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, progressBar, -10, SpringLayout.EAST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, splitPane, 0, SpringLayout.WEST, progressBar);
        frame.getContentPane().add(progressBar);
        SpringLayout sl_panel = new SpringLayout();
        
        JToolBar toolBar = new JToolBar();
        toolBar.setMinimumSize(new Dimension(900,70));
        springLayout.putConstraint(SpringLayout.NORTH, toolBar, 10, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, toolBar, 0, SpringLayout.WEST, splitPane);
        springLayout.putConstraint(SpringLayout.SOUTH, toolBar, -6, SpringLayout.NORTH, splitPane);
        springLayout.putConstraint(SpringLayout.EAST, toolBar, 0, SpringLayout.EAST, splitPane);
        toolBar.setFloatable(false);
        frame.getContentPane().add(toolBar);
       
        
        JButton btn1 = new JButton("Novo [crtl-n]");
        btn1.setBackground(new Color(255, 255, 255));
        btn1.setHorizontalAlignment(SwingConstants.TRAILING);
        btn1.setHorizontalTextPosition(SwingConstants.CENTER);
        btn1.setVerticalAlignment(SwingConstants.BOTTOM);
        btn1.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn1.setIcon(new ImageIcon(Main.class.getResource("/icons/novo.png")));
        btn1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                util.metodoNovo();
            }
        });
        
        JButton btn2 = new JButton("Abrir [crtl-o]");
        btn2.setBackground(new Color(255, 255, 255));
        btn2.setHorizontalAlignment(SwingConstants.TRAILING);
        btn2.setHorizontalTextPosition(SwingConstants.CENTER);
        btn2.setVerticalAlignment(SwingConstants.BOTTOM);
        btn2.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn2.setIcon(new ImageIcon(Main.class.getResource("/icons/abrir.png")));
        btn2.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              util.metodoAbrir();
            }
        });
        
        JButton btn3 = new JButton("Salvar [crtl-s]");
        btn3.setBackground(new Color(255, 255, 255));
        btn3.setHorizontalAlignment(SwingConstants.TRAILING);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalAlignment(SwingConstants.BOTTOM);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn3.setIcon(new ImageIcon(Main.class.getResource("/icons/salvar.png")));
        btn3.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                util.metodoSalvar();
            }
        });
        
        JButton btn4 = new JButton("Copiar [crtl-c]");
        btn4.setBackground(new Color(255, 255, 255));
        btn4.setHorizontalAlignment(SwingConstants.TRAILING);
        btn4.setHorizontalTextPosition(SwingConstants.CENTER);
        btn4.setVerticalAlignment(SwingConstants.BOTTOM);
        btn4.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn4.setIcon(new ImageIcon(Main.class.getResource("/icons/copiar.png")));
        btn4.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                util.metodoCopiar();
            }
        });
        
        JButton btn5 = new JButton("Colar [crtl-v]");
        btn5.setBackground(new Color(255, 255, 255));
        btn5.setHorizontalAlignment(SwingConstants.TRAILING);
        btn5.setHorizontalTextPosition(SwingConstants.CENTER);
        btn5.setVerticalAlignment(SwingConstants.BOTTOM);
        btn5.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn5.setIcon(new ImageIcon(Main.class.getResource("/icons/colar.png")));
        btn5.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                util.metodoColar();
            }
        });
        
        JButton btn6 = new JButton("Recortar [crtl-x]");
        btn6.setBackground(new Color(255, 255, 255));
        btn6.setHorizontalAlignment(SwingConstants.TRAILING);
        btn6.setHorizontalTextPosition(SwingConstants.CENTER);
        btn6.setVerticalAlignment(SwingConstants.BOTTOM);
        btn6.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn6.setIcon(new ImageIcon(Main.class.getResource("/icons/recortar.png")));
        btn6.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btn6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				util.metodoRecortar();
				
			}
		});
        
        JButton btn7 = new JButton("Compilar [F7]");
        btn7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		util.metodoCompilar();
        	}
        });
        btn7.setBackground(new Color(255, 255, 255));
        btn7.setHorizontalAlignment(SwingConstants.TRAILING);
        btn7.setHorizontalTextPosition(SwingConstants.CENTER);
        btn7.setVerticalAlignment(SwingConstants.BOTTOM);
        btn7.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn7.setIcon(new ImageIcon(Main.class.getResource("/icons/compilar.png")));
        btn7.setFont(new Font("Tahoma", Font.PLAIN, 10));
        
        JButton btn8 = new JButton("Equipe [F1]");
        btn8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		util.metodoMostraEquipe();
        	}
        });
        btn8.setBackground(new Color(255, 255, 255));
        btn8.setHorizontalAlignment(SwingConstants.TRAILING);
        btn8.setHorizontalTextPosition(SwingConstants.CENTER);
        btn8.setVerticalAlignment(SwingConstants.BOTTOM);
        btn8.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn8.setIcon(new ImageIcon(Main.class.getResource("/icons/equipe.png")));
        btn8.setFont(new Font("Tahoma", Font.PLAIN, 10));
       
        toolBar.add(btn1);
        toolBar.add(btn2);
        toolBar.add(btn3);
        toolBar.add(btn4);
        toolBar.add(btn5);
        toolBar.add(btn6);
        toolBar.add(btn7);
        toolBar.add(btn8);
    }
}
