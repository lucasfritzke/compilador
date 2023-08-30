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
    private boolean keyCPressed = false;
    private boolean keyXPressed = false;
    private boolean keyF7Pressed = false;
    private boolean keyF1Pressed = false;


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
        if(code == KeyEvent.VK_F1){
            keyF1Pressed = true;
        }
        if(code == KeyEvent.VK_F7){
            keyF7Pressed = true;
        }
        if(code == KeyEvent.VK_C){
            keyCPressed = true;
        }
        if(code == KeyEvent.VK_X){
            keyXPressed = true;
        }

        //Combinação de teclas
        if(keyF1Pressed == true){
            keyF1Pressed = false;
            util.metodoMostraEquipe();
        }
        if(keyF7Pressed == true){
            keyF7Pressed = false;
            util.metodoCompilar();
        }
        if(keyCTRLPressed == true && keyCPressed == true) {
            keyCPressed = false;
            keyCTRLPressed = false;
            util.metodoCopiar();
        }
        if(keyCTRLPressed == true && keyXPressed == true) {
            keyCPressed = false;
            keyCTRLPressed = false;
            util.metodoRecortar();
        }
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
