package gals;
import java.util.regex.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Util.CelulaTabelaSimbolos;

public class Semantico implements Constants {


    private String filename;
    private Stack<String> pilha_tipos = new Stack<String>();
    private HashMap<String, CelulaTabelaSimbolos> tabela_simbolos = new HashMap();
    private Stack<String> pilha_rotulos;
    private ArrayList<String> lista_id;

    private String buffer = "";

    public Semantico(String name){
        this.filename = name;
    }

    //1º) #100, #101, #102, #114, #115
    public void  executeAction(int action, Token token) throws SemanticError {
        //buffer += "A��o #" + action + ", Token: " + token;
        System.out.println("A��o #" + action + ", Token: " + token);

        String tipo="";
        switch (action) {
    
            case 100:
                buffer += (".assembly extern mscorlib {}\n"
                + ".assembly _exemplo{}\n"
                + ".module _exemplo.exe\n"
                + "\n"
                + ".class public "+filename+" {\n"
                + ".method static public void _principal(){\n"
                + ".entrypoint\n");
                break;
            case 101:
                buffer += ("ret\n"
                + "}\n"
                + "}");
                break;
            case 114:
                tipo = "int64";
                pilha_tipos.push(tipo);
                buffer += "ldc.i8 "+token.getLexeme()+"\n";
                buffer += "conv.r8\n";
                break;
            case 115:
                tipo = "float64";
                pilha_tipos.push(tipo);
                buffer += "ldc.r8 "+token.getLexeme()+"\n";
                break;
            case 102:
                tipo = pilha_tipos.pop();
                if(tipo.equals("int64")){
                    buffer +="conv.i8\n";
                } 
                buffer +="call void [mscorlib]System.Console::WriteLine("+tipo+")\n";

                break;
            case 125:
                lista_id.add(token.getLexeme());
                break;
            case 126:
                // verificar se o identificador foi declarado, ou seja, se está na tabela_simbolos; 
                if(tabela_simbolos.containsKey(token.getLexeme())){
                    //em caso positivo, encerrar a execução e apontar erro semantico, indicando a linha e apresentando a mensagem token.getLexeme já declarado
                    throw new SemanticError(token.getLexeme() + " ja declarado");
                } else {
                    // 
                }
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
    

    public String tipoVariavel(String var){
        Matcher matcher;
        return null;
    }

}
