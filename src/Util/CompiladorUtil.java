package Util;

import java.awt.Component;
import java.awt.FileDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CompiladorUtil {
    
    private ArrayList components = new ArrayList();
    private String path = null;

    public void addComponent(Component comp){
        components.add(comp);
    }

    public void metodoNovo(){
        //Inicializa para comporar classes
        JTextArea jTextArea = new JTextArea();
        JTextField jTextField = new JTextField();
        path = null;

        jTextField = (JTextField) geComponentByName("StatusBar");
        jTextField.setText("");

        jTextArea = (JTextArea) geComponentByName("CodeBlock");
        jTextArea.setText("");
    
        
    }

    public void metodoAbrir(){
        //Checando se o caminho foi alterado
        String pathTemp = null;
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();

        if(f.length > 0){
            pathTemp = fd.getFiles()[0].getAbsolutePath();
        }

        if(pathTemp != null && !pathTemp.equals(path)){
            path = pathTemp;
            try {
                File file = new File(path);
                Scanner myReader = new Scanner(file);
                String data = "";
                while(myReader.hasNextLine()){
                    data += myReader.nextLine() + "\n";
                }
                JTextField jTextField = (JTextField) geComponentByName("StatusBar");
                jTextField.setText(path); 

                JTextArea jTextArea = (JTextArea) geComponentByName("CodeBlock");
                jTextArea.setText(data);
                myReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Component geComponentByName(String name){
          for(int i = 0; i < components.size(); i++){
            Component c = (Component) components.get(i); 
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
}
