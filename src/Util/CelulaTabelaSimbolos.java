package Util;

public class CelulaTabelaSimbolos {
    private String identificador;
    private String tipo;
    private String valor;

    public CelulaTabelaSimbolos(String identificador, String tipo, String valor) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.valor = valor;
    }

    public CelulaTabelaSimbolos() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
