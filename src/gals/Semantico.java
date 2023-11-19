package gals;



public class Semantico implements Constants {

    public String  executeAction(int action, Token token) throws SemanticError {
        System.out.println("A��o #" + action + ", Token: " + token);
        String str ="";

        switch (action) {
            case 105:
                return "conv.r";
        
            default:
                return " ";
        }
    
    }

    

    

}
