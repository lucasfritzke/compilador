package Util;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
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

    public void metodoSalvar(){

        if(path == null){
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(new JFrame());

            if(returnVal == JFileChooser.APPROVE_OPTION){
                try {
                    File file = fc.getSelectedFile();
                        if(file.createNewFile()){
                        path = file.getAbsolutePath();

                        JTextArea textArea = (JTextArea) geComponentByName("CodeBlock");
                        String text = textArea.getText();
                        JTextField jTextField = (JTextField) geComponentByName("StatusBar");
                        jTextField.setText(path);

                        String[] splitString = text.split("\n");

                        FileWriter fw = new FileWriter(file);
                        BufferedWriter out = new BufferedWriter(fw);
                        for(int i = 0; i < splitString.length; i++){
                            out.write(splitString[i]);
                        }
                        out.flush();
                        out.close();
                    }
                
                    
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else{
            File file = new File(path);
            if(file.exists()){

                JTextArea textArea = (JTextArea) geComponentByName("CodeBlock");
                String text = textArea.getText();
                JTextField jTextField = (JTextField) geComponentByName("StatusBar");
                jTextField.setText(path);

                String[] splitString = text.split("\n");

                try {
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter out = new BufferedWriter(fw);
                    for(int i = 0; i < splitString.length; i++){
                        out.write(splitString[i] + "\n");
                    }
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }

        }
        
    }
    
    public void metodoCompilar() {
    	JTextArea m = (JTextArea) geComponentByName("MessageBlock");
    	m.setText("Compilação de programas ainda não foi implementada!");
    	
    }
    
    public void metodoMostraEquipe() {
    	JTextArea m = (JTextArea) geComponentByName("MessageBlock");
    	m.setText("Equipe:Guilherme W. Back, João Victor Schmidt e Lucas Fritzke");
    	
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
