package gals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Semantico implements Constants {

    Semantico(String name){
        filename = name;
    }
    private String filename;
    private Stack<String> pilha_tipos;
    private Stack<String> pilha_rotulos;
    private ArrayList<String> lista_id;
    private String buffer;

    //1º) #100, #101, #102, #114, #115
    public void  executeAction(int action, Token token) throws SemanticError {
        //buffer += "A��o #" + action + ", Token: " + token;
        //System.out.println("A��o #" + action + ", Token: " + token);


        switch (action) {
            case 100:
                buffer += (".assembly extern mscorlib {}\n"
                + ".assembly _exemplo{}\n"
                + ".module _exemplo.exe\n"
                + "\n"
                + ".class {{@}}");
                break;
        
            default:
                break;
        }

    }

    public void createFile(String path){

        try{
            File file = new File(correctFileName(path));
            if(file.createNewFile()){
                Path rawPath = Paths.get(file.getAbsolutePath());
                Files.write(rawPath, buffer.getBytes());
                System.out.println("FILE CREATED AT " + file.getAbsolutePath());
            }
        }catch(IOException e){
            System.out.println("File not created");
            e.printStackTrace();
        }
    }

    public String correctFileName(String fileName){
        if(fileName.endsWith(".txt")){
            fileName = fileName.replace(".txt", ".il");
        }
        return fileName;
    }
    

    

}
