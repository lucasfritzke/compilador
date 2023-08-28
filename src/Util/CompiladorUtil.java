package Util;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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
import javax.swing.text.Caret;


public class CompiladorUtil {
    
    private ArrayList components = new ArrayList();
    private String path = null;
    private String copiedText = null;

    public void addComponent(Component comp){
        components.add(comp);
    }

    public void metodoNovo(){
        //Inicializa para comporar classes
        JTextArea jTextArea = new JTextArea();
        JTextField jTextField = new JTextField();
        path = null;

        jTextField = (JTextField) getComponentByName("StatusBar");
        jTextField.setText("");

        jTextArea = (JTextArea) getComponentByName("CodeBlock");
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
                JTextField jTextField = (JTextField) getComponentByName("StatusBar");
                jTextField.setText(path); 

                JTextArea jTextArea = (JTextArea) getComponentByName("CodeBlock");
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

                        JTextArea textArea = (JTextArea) getComponentByName("CodeBlock");
                        String text = textArea.getText();
                        JTextField jTextField = (JTextField) getComponentByName("StatusBar");
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

                JTextArea textArea = (JTextArea) getComponentByName("CodeBlock");
                String text = textArea.getText();
                JTextField jTextField = (JTextField) getComponentByName("StatusBar");
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
    	JTextArea m = (JTextArea) getComponentByName("MessageBlock");
    	m.setText("Compilação de programas ainda não foi implementada!");
    	
    }
    
    public void metodoMostraEquipe() {
    	JTextArea m = (JTextArea) getComponentByName("MessageBlock");
    	m.setText("Equipe:Guilherme W. Back, João Victor Schmidt e Lucas Fritzke");
    	
    }

    public void metodoCopiar() {
        JTextArea jTextArea1 = (JTextArea) getComponentByName("CodeBlock");
        JTextArea jTextArea2 = (JTextArea) getComponentByName("MessageBlock");
        
        String selected1 = jTextArea1.getSelectedText();
        String selected2 = jTextArea2.getSelectedText();
        
        if(selected1 != null && !selected1.equals("") ){
            copiedText = selected1;
            StringSelection stringSelection = new StringSelection(selected1);
            java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
        else if(selected2 != null && !selected2.equals("")){
            copiedText = selected2;
            StringSelection stringSelection = new StringSelection(selected2);
            java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }

    public void metodoColar(){
        JTextArea jTextArea = (JTextArea) getComponentByName("CodeBlock");
        int caret = jTextArea.getCaretPosition();
        char[] charArr = jTextArea.getText().toCharArray();
        charArr = novoCharArr(charArr, caret);
        String text = new String(charArr);
        jTextArea.setText(text);

    }

    //TODO
    private char[] novoCharArr(char[] charArr, int caret){
        char[] novoCharArr = new char[charArr.length + copiedText.length()];
        //populating the vector
        for(int i = 0; i < charArr.length; i++){
            novoCharArr[i] = charArr[i];
        }

        //setting up the string
        char[] text = copiedText.toCharArray();
        for(int i = caret, z = 0; i < novoCharArr.length; i++, z++){
            if(novoCharArr[i] == 0 && z < text.length){
                novoCharArr[i] = text[z];
            }
            else if(i+1 < novoCharArr.length && novoCharArr[i] != 0 && z < text.length){
                novoCharArr[i+1] = novoCharArr[i];
                novoCharArr[i] = text[z];
            }
        }
        return novoCharArr;
    }


    public Component getComponentByName(String name){
          for(int i = 0; i < components.size(); i++){
            Component c = (Component) components.get(i); 
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

}
