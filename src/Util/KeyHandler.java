package Util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyHandler implements KeyListener {

    private CompiladorUtil util;

    //Supervised keys
    private boolean keyNPressed = false;
    private boolean keyCTRLPressed = false;
    private boolean keyOPressed = false;
    private boolean keySPressed = false;

    public KeyHandler(CompiladorUtil util){
        this.util = util;
    }

    public void keyPressed(KeyEvent key) {
        int code = key.getKeyCode();
        if(code == KeyEvent.VK_N){
            keyNPressed = true;
        }
         if(code == KeyEvent.VK_CONTROL){
            keyCTRLPressed = true;
        }
        if(code == KeyEvent.VK_O){
            keyOPressed = true;
        }
        if(code == KeyEvent.VK_S){
            keySPressed = true;
        }

        //Combinação de teclas
        if(keyCTRLPressed == true && keySPressed == true){
            keyCTRLPressed = false;
            keySPressed = false;
            util.metodoSalvar();
        }
        if(keyOPressed == true && keyCTRLPressed == true){
            keyOPressed = false;
            keyCTRLPressed = false;
            util.metodoAbrir();
        }
        if(keyCTRLPressed == true && keyNPressed == true){
            keyNPressed = false;
            keyCTRLPressed = false;
            util.metodoNovo();
        }
    }

    public void keyReleased(KeyEvent key) {
        int code = key.getKeyCode();
        if(code == KeyEvent.VK_N){
            keyNPressed = false;
        }
         if(code == KeyEvent.VK_CONTROL){
            keyCTRLPressed = false;
        }
        if(code == KeyEvent.VK_O){
            keyOPressed = false;
        }
        if(code == KeyEvent.VK_S){
            keySPressed = false;
        }

    }

    public void keyTyped(KeyEvent arg0) {}
    
}
