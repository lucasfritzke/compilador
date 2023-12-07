// LUCAS FRITZKE E GUILHERME BACK

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class Dijkstra {

    private ArrayList<Vertice> listaAdj = new ArrayList<>();

    public Dijkstra(ArrayList<Vertice> listaAdj) {
        this.listaAdj = listaAdj;
    }

    public ArrayList<Vertice> getListaAdj() {
        return listaAdj;
    }

    public void setListaAdj(ArrayList<Vertice> listaAdj) {
        this.listaAdj = listaAdj;
    }

    public Vertice getVertice(String id) {
        for (Vertice v : listaAdj) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public void dijkstra(String id) {
        Vertice v = this.getVertice(id);
        if (v != null) {
            this.initializaSingleSource(id);
        } else {
            throw new IllegalArgumentException("Vertice nao existe");
        }

        TreeMap<String, Vertice> verticesProcessados = new TreeMap<>();
        PriorityQueue<Vertice> filaVertices = new PriorityQueue<>(new VerticeDijkstraComparator());
        for (Vertice vertice : listaAdj) {
            filaVertices.add(vertice);
        }

        while (!filaVertices.isEmpty()) {
            Vertice u = filaVertices.poll();
            verticesProcessados.put(u.getId(), u);
            ArrayList<Aresta> listaArestas = u.getListaArestas();
            
            for (Aresta a : listaArestas) {
                if(!verticesProcessados.containsKey(a.getVerticeAdjacente(u).getId())){
                    this.relax(u, a.getVerticeAdjacente(u), a.getCusto(), filaVertices);
                }
            }

        }
    }

    private void initializaSingleSource(String id) {

        for (Vertice v : listaAdj) {
            v.setD(Integer.MAX_VALUE);
            v.setPi("nill");

            if (v.getId().equals(id)) {
                v.setD(0);
                v.setPi("nill");
            }
        }

    }

    private void relax(Vertice u, Vertice verticeAdj, int custo, PriorityQueue<Vertice> q) {

        if (verticeAdj.getD() > u.getD() + custo) {
            q.remove(verticeAdj);
            verticeAdj.setD(u.getD() + custo);
            verticeAdj.setPi(u.getId());
            q.add(verticeAdj);
        }
    }

    public VetorRotasDijkstra getVetorRoteamento(String id) {
        this.dijkstra(id);
        VetorRotasDijkstra v = new VetorRotasDijkstra(listaAdj, id);
        return v;
    }

    class ArestaComparator implements Comparator<Aresta> {
        @Override
        public int compare(Aresta aresta, Aresta outraAresta) {

            return Integer.compare(aresta.getCusto(), outraAresta.getCusto());
        }

    }

    public class VerticeDijkstraComparator implements Comparator<Vertice> {

        @Override
        public int compare(Vertice o1, Vertice o2) {
            return Integer.compare(o1.getD(), o2.getD());
        }

    }

}
