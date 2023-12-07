// LUCAS FRITZKE E GUILHERME BACK

import java.util.ArrayList;
import java.util.List;

public class CarteiroChines {

    private ArrayList<Vertice> listaAdj = new ArrayList<>();
    private Grafo grafo = new Grafo();
    private StringBuilder str = new StringBuilder();

    public CarteiroChines(Grafo gr) {
        this.grafo = gr;
    }

    public String calcularRota(String id) {
        
        grafo.zerarFlags();
       // System.out.println(grafo.toString());
        if(grafo.isConexo()){
        
            str.append("---------------------- EXECUÇÃO ALGORITMO CARTEIRO CHINES ----------------------");
            str.append("\n");
            str.append("\n");
            List<Vertice> listaVerticesImpares = grafo.getVerticesGrauImpar();
            if (!listaVerticesImpares.isEmpty()) {
                str.append(this.verticesImparToString(listaVerticesImpares));
                List<VetorRotasDijkstra> vetoresRotaDijkstras;

                vetoresRotaDijkstras = this.calcularCaminhoMinimo(listaVerticesImpares);
                MatrizCusto matrizCusto = new MatrizCusto(vetoresRotaDijkstras, listaVerticesImpares);
                str.append("------------------------------- MATRIZ DE CUSTO -------------------------------");
                str.append("\n");
                str.append("\n");
                str.append(matrizCusto.toString() + "\n");
                List<CelulaMatrizCusto> caminhos = matrizCusto.getVerticesMenorCaminho();
                this.criarCaminhosArtificiais(caminhos, vetoresRotaDijkstras);

                int custo = grafo.getCustoTotal();
                str.append("\n\n------------------------------- CICLO EULERIANO: -------------------------------");
                str.append("\n \n");
                String cicloEuleriano = grafo.gerarCicloEuleriano(id);;
                //System.out.println(grafo.toString());
                //System.out.println(custo);

                str.append(cicloEuleriano+"\n \n");
                str.append("CUSTO TOTAL: "+ custo+"\n");

            } else {

                int custo = grafo.getCustoTotal();
                str.append("\n\n------------------------------- CICLO EULERIANO: -------------------------------");
                str.append("\n \n");
                String cicloEuleriano = grafo.gerarCicloEuleriano(id);;
                
                str.append("grafo é por padrão euleriano");
                
                str.append(cicloEuleriano+"\n \n");
                str.append("CUSTO TOTAL: "+ custo+"\n");

            }
        }else{
            str.append("Grafo não é conexo");
        }
        return str.toString();
    }

    private List<VetorRotasDijkstra> calcularCaminhoMinimo(List<Vertice> listaVerticesImpares) {
        str.append("\n");
        str.append("\n");
        str.append("------------------------ CAMINHO MINIMO DE CADA VERTICE ------------------------");
        str.append("\n");
        List<VetorRotasDijkstra> vetoresRotaDijkstras = new ArrayList<>();
        Dijkstra d = new Dijkstra(grafo.getListaAdj());
        for (Vertice v : listaVerticesImpares) {
            VetorRotasDijkstra vrd = d.getVetorRoteamento(v.getId());
            vetoresRotaDijkstras.add(vrd);
            str.append("Dijkstra Vertice: " + vrd.getId() + "\n");
            str.append(vrd.toString() + "\n\n");
        }
        return vetoresRotaDijkstras;
    }

    private String verticesImparToString(List<Vertice> listaVerticesImpares) {
        StringBuilder str = new StringBuilder();
        str.append("Vertices Impares = { ");
        for (Vertice v : listaVerticesImpares) {
            str.append(v.getId() + ", ");
        }
        str.append("}");
        String st = str.toString();
        st = st.replaceAll(", }", " }");
        
        return st;
    }

    private void criarCaminhosArtificiais(List<CelulaMatrizCusto> caminhos,
        List<VetorRotasDijkstra> vetoresRotaDijkstras) {
        int cont=1;
        for (CelulaMatrizCusto caminho : caminhos) {
            String idVerticeInicial = caminho.getIdVertice();
            String idVeStringFinal = caminho.getIdVerticeAdj();
            str.append("Caminho "+ cont+ "-> entre vertices");
            cont++;
            for(VetorRotasDijkstra vetor : vetoresRotaDijkstras){
                if(vetor.getId().equals(idVerticeInicial)){
                    String liVertices = "";
                   String v = this.duplicarArestas(vetor, caminho.getCusto(),liVertices, idVeStringFinal); 
                   str.append(" "+v);                   
                }
            }
            str.append("custo: "+caminho.getCusto()+"\n");
        }
    }

    public String duplicarArestas(VetorRotasDijkstra vetor, int custo, String str, String idVertice){
        str += idVertice+" ";
        int custoVerticeFinal = vetor.getDistancia(idVertice);
        String pai = vetor.getPai(idVertice);
        if(pai.equals("nill")){
            return str;
        }
        int custoPai = vetor.getDistancia(pai);
        grafo.duplicarArestas(idVertice, pai, (custoVerticeFinal-custoPai));
        custo = custo - custoPai;
        return duplicarArestas(vetor, custo, str, pai);
    }




    public boolean isEuleriano() {
        for (Vertice v : listaAdj) {
            if (v.getListaAdj().length() % 2 != 0) {

                return false;
            }
        }

        return true;

    }

    public Grafo getGr() {
        return grafo;
    }

    public void setGr(Grafo gr) {
        this.grafo = gr;
    }

}
