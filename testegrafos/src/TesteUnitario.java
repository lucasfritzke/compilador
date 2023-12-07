// LUCAS FRITZKE E GUILHERME BACK

public class TesteUnitario {

    public static void main(String[] args) {
        
        Grafo grafo = new Grafo();
        grafo.addVertice("A");
        grafo.addVertice("B");
        grafo.addVertice("C");
        grafo.addVertice("D");
        grafo.addVertice("E");
        grafo.addVertice("F");
        grafo.addVertice("G");
        grafo.addVertice("H");
        grafo.addVertice("I");
        grafo.addVertice("J");
        grafo.addVertice("K");
        grafo.addVertice("L");

        grafo.addAresta("A", "E", 450);
        grafo.addAresta("A", "B", 342);
        grafo.addAresta("E", "F", 230);
        grafo.addAresta("F", "B", 280);
        grafo.addAresta("F", "G", 235);
        grafo.addAresta("B", "C", 61);
        grafo.addAresta("G", "C", 279);
        grafo.addAresta("C", "D", 118);
        grafo.addAresta("D", "H", 310);
        grafo.addAresta("G", "H", 196);
        grafo.addAresta("G", "I", 243);
        grafo.addAresta("H", "J", 168);
        grafo.addAresta("I", "J", 175);
        grafo.addAresta("I", "K", 248);
        grafo.addAresta("K", "L", 128);
        grafo.addAresta("J", "L", 241);

        CarteiroChines cc = new CarteiroChines(grafo);
        System.out.println(cc.calcularRota("A"));

    }
    
}
