// LUCAS FRITZKE E GUILHERME BACK

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {

    private ArrayList<Vertice> listaAdj = new ArrayList<>();
    private StringBuilder str = new StringBuilder();

    public void addVertice(Vertice v) {

        if (listaAdj.contains(v)) {
            throw new IllegalArgumentException("Verice ja adicionado");
        } else {
            listaAdj.add(v);
        }
        listaAdj.sort(null);

    }

    public void addVertice(String id) {

        Vertice v = new Vertice(id);
        if (listaAdj.contains(v)) {
            throw new IllegalArgumentException("Verice ja adicionado");
        } else {
            listaAdj.add(v);
        }
        listaAdj.sort(null);
    }

    public ArrayList<Vertice> getListaAdj() {
        return this.listaAdj;

    }

    public void addAresta(String idVertice1, String idVertice2, int custo) {

        Vertice v1 = this.getVertice(idVertice1);
        Vertice v2 = this.getVertice(idVertice2);

        if (v1 != null && v2 != null && !v1.equals(v2)) {
            Aresta a = new Aresta(v1, v2, custo);
            v1.addAresta(a);
            v2.addAresta(a);
        } else {
            throw new IllegalArgumentException("Entrada invalida: Vertice nao encontrado");
        }
    }

    public Vertice getVertice(String id) {

        for (Vertice v : listaAdj) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public String getVetorRotasDijkstra(String id) {
        Dijkstra d = new Dijkstra(listaAdj);
        VetorRotasDijkstra ve = d.getVetorRoteamento(id);
        return ve.toString();
    }

    public List<Vertice> getVerticesGrauImpar() {
        List<Vertice> li = new ArrayList<>();
        for (Vertice v : listaAdj) {
            if (v.getListaArestas().size() % 2 != 0) {
                li.add(v);
                ;
            }
        }

        return li;
    }

    public void duplicarArestas(Vertice v1, Vertice v2, int custo) {
        Aresta aTemp = new Aresta(v1, v2, custo);

        for (Vertice vertice : listaAdj) {
            ArrayList<Aresta> liAresta = vertice.getListaArestas();
            for (Aresta aresta : liAresta) {
                if (aresta.equals(aTemp)) {
                    aresta.setDuplicacao(true);
                    v1.addAresta(aTemp);
                    v2.addAresta(aTemp);
                    return;
                }
            }
        }
    }

    public void duplicarArestas(Aresta a) {
        Vertice[] v = a.getVertices();
        Aresta aTemp = new Aresta(v[0], v[1], a.getCusto());

        for (Vertice vertice : listaAdj) {
            ArrayList<Aresta> liAresta = vertice.getListaArestas();
            for (Aresta aresta : liAresta) {
                if (aresta.equals(aTemp)) {
                    aresta.setDuplicacao(true);
                    v[0].addAresta(aTemp);
                    v[1].addAresta(aTemp);
                    return;
                }
            }
        }
    }

    public void duplicarArestas(String id1, String id2, int custo) {

        Vertice v1 = this.getVertice(id1);
        Vertice v2 = this.getVertice(id2);
        Aresta aTemp = new Aresta(v1, v2, custo);
        Aresta aTemp2 = new Aresta(v2, v1, custo);

        for (Vertice vertice : listaAdj) {
            ArrayList<Aresta> liAresta = vertice.getListaArestas();
            for (Aresta aresta : liAresta) {
                if (aresta.equals(aTemp)) {
                    aresta.setDuplicacao(true);
                    v1.addAresta(aTemp);
                    v2.addAresta(aTemp);
                    return;
                } else if (aresta.equals(aTemp2)) {
                    aresta.setDuplicacao(true);
                    v1.addAresta(aTemp2);
                    v2.addAresta(aTemp2);
                    return;
                }
            }
        }
    }

    public void removerDuplicacoes() {

        Iterator<Vertice> iteratorVertice = this.listaAdj.iterator();
        while (iteratorVertice.hasNext()) {
            Vertice v = iteratorVertice.next();
            Iterator<Aresta> iteratorAresta = v.getListaArestas().iterator();
            while (iteratorAresta.hasNext()) {
                Aresta a = iteratorAresta.next();
                if (a.isDuplicacao()) {
                    iteratorAresta.remove();
                }
            }

        }

    }

    @Override
    public String toString() {
        String str = " ";
        for (Vertice v : listaAdj) {
            str += v.getListaAdj() + "\n";
        }
        return str;
    }

    public Aresta getAresta(String string, String string2, int i) {
        Vertice v1 = this.getVertice(string);
        Vertice v2 = this.getVertice(string2);
        Aresta temp = new Aresta(v1, v2, i);
        for (Vertice v : listaAdj) {
            ArrayList<Aresta> liAr = v.getListaArestas();
            for (Aresta aresta : liAr) {
                if (aresta.equals(temp)) {
                    return aresta;
                }
            }

        }
        return null;
    }

    public void zerarFlags() {
        this.removerDuplicacoes();
        for (Vertice vertice : listaAdj) {
            vertice.setFlag(' ');
            ArrayList<Aresta> liAr = vertice.getListaArestas();
            for (Aresta aresta : liAr) {
                aresta.setFlag(' ');
            }

        }

    }

    public int getCustoTotal() {
        int cont = 0;

        for (Vertice vertice : listaAdj) {

            ArrayList<Aresta> liAr = vertice.getListaArestas();
            for (Aresta aresta : liAr) {
                cont += aresta.getCusto();
            }

        }

        return cont / 2;
    }

    public String gerarCicloEuleriano(String id) {
        str = new StringBuilder();
        Vertice v = this.getVertice(id);
        percorrerGrafo(v);
        String s = str.toString();
        int tam = s.length();
        
        return s.substring(0, tam-2);
    }

    public void percorrerGrafo(Vertice v) {

        str.append(v.getId() + " - "); // ADD VERTICE VISITADO
        List<Aresta> arestasValidas = this.getArestasValidas(v);

        if (arestasValidas.size() == 1) { // SE FOR O UNICO NAO PRECISA TESTAR SE E PONTE
            Aresta a = arestasValidas.get(0);
            v.setFlag('e');
            a.setFlag('e');
            Vertice vAdj = a.getVerticeAdjacente(v);
            percorrerGrafo(vAdj);
            return;
        } else {
            for (Aresta a : arestasValidas) {

                if (!this.isPonte(v, a)) {
                    a.setFlag('e');
                    Vertice vAdj = a.getVerticeAdjacente(v);
                    
                    percorrerGrafo(vAdj);
                    return;
                }
            }
        }

    }

    private boolean isPonte(Vertice v, Aresta a) {
        this.zerarFlagsBusca();
        a.setFlagBusca('p');
        int qtdVerticesValidos = 0;
        for (Vertice vertice : listaAdj) {
            if (vertice.getFlag() != 'e') {
                qtdVerticesValidos++;
            }
        }

        PriorityQueue<Vertice> fila = new PriorityQueue<>();
        HashMap<String, Vertice> verticesDescobertos = new HashMap<>();
        verticesDescobertos.put(v.getId(), v);
        fila.add(v);
        while (!fila.isEmpty()) {
            Vertice v1 = fila.poll();
            ArrayList<Aresta> listaArestas = v1.getListaArestas();
            for (Aresta aresta : listaArestas) {
                if (aresta.getFlagBusca() != 'e' && aresta.getFlagBusca() != 'p' && aresta.getFlag() != 'e') {
                    if (!verticesDescobertos.containsKey(aresta.getVerticeAdjacente(v1).getId())) {
                        fila.add(aresta.getVerticeAdjacente(v1));
                        verticesDescobertos.put(aresta.getVerticeAdjacente(v1).getId(), v1);
                        aresta.setFlagBusca('e');
                    } 
                }
            }
        }

        if (verticesDescobertos.size() == qtdVerticesValidos) {
            this.zerarFlagsBusca();
            return false;
        } else {
            this.zerarFlagsBusca();
            return true;
        }

    }

    private void zerarFlagsBusca() {

        for (Vertice vertice : listaAdj) {
            ArrayList<Aresta> liAr = vertice.getListaArestas();
            for (Aresta aresta : liAr) {
                aresta.setFlagBusca(' ');
            }

        }
    }

    private List<Aresta> getArestasValidas(Vertice v) {
        ArrayList<Aresta> arestasValidas = new ArrayList<>();
        ArrayList<Aresta> arestas = v.getListaArestas();
        for (Aresta aresta : arestas) {
            if (aresta.getFlag() != 'e') {
                arestasValidas.add(aresta);
            }
        }

        return arestasValidas;
    }

    public boolean isConexo(){
        int qtdVerticesValidos = this.listaAdj.size();
        
        Vertice v = listaAdj.get(0);
        Aresta a = v.getListaArestas().isEmpty() == true ? null : v.getListaArestas().get(0) ;

        if(a == null){
            return false;
        }

        this.zerarFlagsBusca();
        a.setFlagBusca('p');

        PriorityQueue<Vertice> fila = new PriorityQueue<>();
        HashMap<String, Vertice> verticesDescobertos = new HashMap<>();
        verticesDescobertos.put(v.getId(), v);
        fila.add(v);
        while (!fila.isEmpty()) {
            Vertice v1 = fila.poll();
            ArrayList<Aresta> listaArestas = v1.getListaArestas();
            for (Aresta aresta : listaArestas) {
                if (aresta.getFlagBusca() != 'e' && aresta.getFlagBusca() != 'p' && aresta.getFlag() != 'e') {
                    if (!verticesDescobertos.containsKey(aresta.getVerticeAdjacente(v1).getId())) {
                        fila.add(aresta.getVerticeAdjacente(v1));
                        verticesDescobertos.put(aresta.getVerticeAdjacente(v1).getId(), v1);
                        aresta.setFlagBusca('e');
                    } 
                }
            }
        }

        if (verticesDescobertos.size() == qtdVerticesValidos) {
            this.zerarFlagsBusca();
            return true;
        } 
        this.zerarFlagsBusca();
        return false;
    }
}