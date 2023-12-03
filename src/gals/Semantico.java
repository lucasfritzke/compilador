package gals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Util.CelulaTabelaSimbolos;

//import Util.CelulaTabelaSimbolos;

public class Semantico implements Constants {

    private String filename;
    private Stack<String> pilha_tipos = new Stack<String>();
    private HashMap<String, CelulaTabelaSimbolos> tabela_simbolos = new HashMap();
    private Stack<String> pilha_rotulos;
    private ArrayList<Token> lista_id = new ArrayList<Token>();
    private String operador_relacional;

    private String buffer = "";

    public Semantico(String name) {
        this.filename = name;
    }

    // 1º) #100, #101, #102, #114, #115
    public void executeAction(int action, Token token) throws SemanticError {
        // buffer += "A��o #" + action + ", Token: " + token;
        System.out.println("A��o #" + action + ", Token: " + token);

        String tipo = "";
        switch (action) {

            case 100:
                buffer += (".assembly extern mscorlib {}\n"
                        + ".assembly _exemplo{}\n"
                        + ".module _exemplo.exe\n"
                        + "\n"
                        + ".class public " + filename + " {\n"
                        + ".method static public void _principal(){\n"
                        + ".entrypoint\n");
                break;
            case 101:
                buffer += ("ret\n"
                        + "}\n"
                        + "}");
                break;
            case 102:
                tipo = pilha_tipos.pop();
                if (tipo.equals("int64")) {
                    buffer += "conv.i8\n";
                }
                buffer += "call void [mscorlib]System.Console::WriteLine(" + tipo + ")\n";

                break;
            case 103:
                String t1 = pilha_tipos.pop();
                String t2 = pilha_tipos.pop();
                String tipoResult = this.verificarTipoResultante(t1, t2, "&");
                pilha_tipos.push(tipoResult);
                buffer += "and\n";
                break;
            case 104:
                t1 = pilha_tipos.pop();
                t2 = pilha_tipos.pop();
                tipoResult = this.verificarTipoResultante(t1, t2, "|");
                pilha_tipos.push(tipoResult);
                buffer += "or\n";
                break;
            case 105:
                pilha_tipos.push("bool");
                buffer += "ldc.i4.1\n";
                break;
            case 106:
                pilha_tipos.push("bool");
                buffer += "ldc.i4.0\n";
                break;
            case 107:
                t1 = pilha_tipos.pop();
                pilha_tipos.push("bool");
                buffer += "ldc.i4.1 \n"
                        + "xor\n";
                break;
            case 108:
                // <operador_relacional>::= "==" | "!=" | "<" | ">" ;
                operador_relacional = verificarOperadorRelacional(operador_relacional);
                break;
            case 109:
                // Feito em parte
                t1 = pilha_tipos.pop();
                t2 = pilha_tipos.pop();
                tipoResult = this.verificarTipoResultante(t1, t2, operador_relacional);
                pilha_tipos.push(tipoResult);
                break;
            case 110:
                // desempilhar dois tipos da pilha_tipos, empilhar o tipo resultante da operação
                // conforme indicado na TABELA DE TIPOS;
                t1 = pilha_tipos.pop();
                t2 = pilha_tipos.pop();
                tipoResult = this.verificarTipoResultante(t1, t2, "+");
                pilha_tipos.push(tipoResult);
                buffer += "add \n";
                break;
            case 111:
                // desempilhar dois tipos da pilha_tipos, empilhar o tipo resultante da operação
                // conforme indicado na TABELA DE TIPOS;
                t1 = pilha_tipos.pop();
                t2 = pilha_tipos.pop();
                tipoResult = this.verificarTipoResultante(t1, t2, "-");
                pilha_tipos.push(tipoResult);
                buffer += "sub \n";
                break;
            case 112:
                // desempilhar dois tipos da pilha_tipos, empilhar o tipo resultante da operação
                // conforme indicado na TABELA DE TIPOS;
                t1 = pilha_tipos.pop();
                t2 = pilha_tipos.pop();
                tipoResult = this.verificarTipoResultante(t1, t2, "*");
                pilha_tipos.push(tipoResult);
                buffer += "mul \n";
                break;
            case 113:
                // desempilhar dois tipos da pilha_tipos, empilhar o tipo resultante da operação
                // conforme indicado na TABELA DE TIPOS;
                t1 = pilha_tipos.pop();
                t2 = pilha_tipos.pop();
                tipoResult = this.verificarTipoResultante(t1, t2, "/");
                pilha_tipos.push(tipoResult);
                buffer += "div \n";
                break;
            case 114:
                tipo = "int64";
                pilha_tipos.push(tipo);
                buffer += "ldc.i8 " + token.getLexeme() + "\n";
                buffer += "conv.r8\n";
                break;
            case 115:
                tipo = "float64";
                pilha_tipos.push(tipo);
                buffer += "ldc.r8 " + token.getLexeme() + "\n";
                break;
            case 116:
                tipo = "string";
                pilha_tipos.push(tipo);
                buffer += "ldstr " + token.getLexeme() + "\n";
                break;
            case 117:
                t1 = pilha_tipos.pop();
                if (t1.equals("int64")) {
                    t2 = "int64";
                    pilha_tipos.push(t2);

                    buffer += "ldc.i8 -1" + "\n";
                    buffer += "conv.r8" + "\n";
                    buffer += "mul" + "\n";
                } else if (t1.equals("float64")) {
                    t2 = "float64";
                    pilha_tipos.push(t2);

                    buffer += "ldc.r8 -1" + "\n";
                    buffer += "mul" + "\n";
                }
                break;
            case 118:
                // Incompleto
                t1 = pilha_tipos.pop();
                if (t1.equals("bool")) {
                    String novo_rotulo1 = "";
                    buffer += "brfalse novo_rotulo1" + "\n";
                    pilha_rotulos.add(novo_rotulo1);
                } else {
                    // Encerrar a execução e apontar erro semântico, indicando a linha e
                    // apresentando a mensagem expressão incompatível em comando de seleção;
                }
                break;
            case 119:
                break;
            case 120:
                break;
            case 121:
                break;
            case 122:
                break;
            case 123:
                break;
            case 124:
                break;
            case 125:
                lista_id.add(token);
                //buffer += "token.getLexeme" + "\n";
                break;
            case 126:
                for (Token t : lista_id) {
                    // verificar se o identificador foi declarado, ou seja, se está na
                    // tabela_simbolos
                    if (tabela_simbolos.containsKey(t.getLexeme())) {
                        throw new SemanticError(t.getLexeme() + " ja declarado", token.getPosition());
                    } else {
                        CelulaTabelaSimbolos c = new CelulaTabelaSimbolos(
                                t.getLexeme(),
                                this.tipoVariavel(t.getLexeme()),
                                token.getLexeme());
                        tabela_simbolos.put(c.getIdentificador(), c);
                        buffer += ".locals (" + c.getTipo() + " " + c.getIdentificador() + ")\n";
                    }
                }
                lista_id.clear();
                break;
            case 127:
                for (Token t : lista_id) {
                    // verificar se o identificador foi declarado, ou seja, se está na
                    // tabela_simbolos
                    if (tabela_simbolos.containsKey(t.getLexeme())) {
                        throw new SemanticError(t.getLexeme() + " ja declarado", token.getPosition());
                    } else {
                        CelulaTabelaSimbolos c = new CelulaTabelaSimbolos(
                                t.getLexeme(),
                                this.tipoVariavel(t.getLexeme()),
                                null);
                        tabela_simbolos.put(c.getIdentificador(), c);
                        buffer += ".locals (" + c.getTipo() + " " + c.getIdentificador() + ")\n";
                    }
                }
                lista_id.clear();
                break;
            case 128:
                // (a) desempilhar o tipo da <expressão> da pilha_tipos;
                tipo = pilha_tipos.pop();
                // (b) gerar o código objeto dup n vezes, onde n é igual a quantidade de
                // identificadores da lista_id menos 1;
                for (int i = 0; i < (lista_id.size() - 1); i++) {
                    buffer += "dup \n";
                }
                // (c) para cada identificador da lista_id:
                for (Token t : lista_id) {
                    // verificar se o identificador foi declarado, ou seja, se está na
                    // tabela_simbolos
                    if (!tabela_simbolos.containsKey(t.getLexeme())) {
                        throw new SemanticError(t.getLexeme() + " não declarado", token.getPosition());
                    } else {
                        if (this.tipoVariavel(t.getLexeme()).equalsIgnoreCase("int64")) {
                            buffer += "conv.i8 \n";
                        }
                        buffer += "stloc " + t.getLexeme() + "\n";
                    }
                }
                lista_id.clear();
                break;
            case 129:
                break;
            case 130:
                buffer += "ldstr" + "\n";
                buffer += "call  void  [mscorlib]System.Console::Write (string)" + "\n";
                break;
            case 131:
                // (a) verificar se o identificador (token.getLexeme) foi declarado, ou seja, se
                // está na tabela_simbolos;
                if (!tabela_simbolos.containsKey(token.getLexeme())) {
                    // em caso negativo, encerrar a execução e apontar erro semântico, indicando a
                    // linha e apresentam a mensagem token.getLexeme não declarado (por exemplo:
                    // _iarea não declarado);
                    throw new SemanticError(token.getLexeme() + " não declarado", token.getPosition());
                } else {
                    CelulaTabelaSimbolos c = tabela_simbolos.get(token.getLexeme());
                    // (c) em caso positivo e é identificador de constante:
                    if (c.getValor() != null) {
                        if (c.getTipo().equals("int64")) {
                            buffer += "ldc.i8 " + c.getValor() + "\n";
                            buffer += "conv.r8 \n";
                            pilha_tipos.push(c.getTipo());
                        } else if (c.getTipo().equals("string")) {
                            buffer += "ldstr " + c.getValor() + "\n";
                        }
                    }
                }
                break;

            default:
                break;

        }

    }

    public void createFile(String path) {

        try {
            File file = new File(correctFileName(path));
            if (file.createNewFile()) {
                Path rawPath = Paths.get(file.getAbsolutePath());
                Files.write(rawPath, buffer.getBytes());
                System.out.println("FILE CREATED AT " + file.getAbsolutePath());
            } else {
                Path rawPath = Paths.get(file.getAbsolutePath());
                Files.write(rawPath, buffer.getBytes());
            }
        } catch (IOException e) {
            System.out.println("File not created");
            e.printStackTrace();
        }
    }

    public String correctFileName(String fileName) {
        if (fileName.endsWith(".txt")) {
            fileName = fileName.replace(".txt", ".il");
        }
        return fileName;
    }

    public String tipoVariavel(String var) {

        if (var.charAt(1) == 'i') {
            return "int64";
        } else if (var.charAt(1) == 'f') {
            return "float64";
        } else if (var.charAt(1) == 's') {
            return "string";
        } else {
            return "bool";
        }
    }

    public String verificarTipoResultante(String t1, String t2, String op) {

        if (t1.equals("int64") && t2.equals("int64")) {
            if (mathematicalOperatorExists(op)) {
                return "int64";
            }
            return "bool";
        } else if (t1.equals("int64") && t2.equals("float64") ||
                t1.equals("float64") && t2.equals("int64") ||
                t1.equals("float64") && t2.equals("float64")) {
            if (!mathematicalOperatorExists(op)) {
                return "bool";
            }
            return "float64";
        } else if (t1.equals("bool") && t2.equals("bool") ||
                t1.equals("string") && t2.equals("string")) {
            return "bool";
        }

        return null;
    }

    private String verificarOperadorRelacional(String operadorRelacional) {

        if (operadorRelacional.equals("==") || operadorRelacional.equals("!=") || operadorRelacional.equals("<")
                || operadorRelacional.equals(">")) {
            return operadorRelacional;
        }

        return null;
    }

    private boolean mathematicalOperatorExists(String op) {
        String[] operatorArr = { "-", "+", "*", "/" };
        for (String c : operatorArr) {
            if (op.equals(c)) {
                return true;
            }
        }
        return false;
    }

}
