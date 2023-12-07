// LUCAS FRITZKE E GUILHERME BACK

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatrizCusto {
    ArrayList<ArrayList<CelulaMatrizCusto>> matriz = new ArrayList<>();
        List<VetorRotasDijkstra> vetoresRotaDijkstras;
        List<Vertice> listaVerticesImpares;

        public MatrizCusto(List<VetorRotasDijkstra> vetoresRotaDijkstras, List<Vertice> listaVerticesImpares) {
            this.vetoresRotaDijkstras = vetoresRotaDijkstras;
            this.listaVerticesImpares = listaVerticesImpares;
            this.gerarMatriz();
        }

        public List<CelulaMatrizCusto> getVerticesMenorCaminho() {

            List<CelulaMatrizCusto> lista = new ArrayList<>();

            while (this.matriz.size() != 0) {

                CelulaMatrizCusto temp = new CelulaMatrizCusto(null, null, Integer.MAX_VALUE);
                // PEGAR MENOR VALOR
                for (ArrayList<CelulaMatrizCusto> linha : matriz) {
                    for (CelulaMatrizCusto c : linha) {
                        if (c.getCusto() < temp.getCusto() && c.getCusto() > 0) {
                            temp = c;
                        }
                    }
                }
                lista.add(temp);
                // ELIMINAR DA MATRIZ VALORES
                Iterator<ArrayList<CelulaMatrizCusto>> linhaIterator = matriz.iterator();
                while (linhaIterator.hasNext()) {
                    List<CelulaMatrizCusto> linha = linhaIterator.next();
                    Iterator<CelulaMatrizCusto> celulaIterator = linha.iterator();
                    while (celulaIterator.hasNext()) {
                        CelulaMatrizCusto c = celulaIterator.next();
                        if (c.getIdVertice().equals(temp.getIdVertice()) ||
                                c.getIdVertice().equals(temp.getIdVerticeAdj()) ||
                                c.getIdVerticeAdj().equals(temp.getIdVerticeAdj()) ||
                                c.getIdVerticeAdj().equals(temp.getIdVertice())) {
                            celulaIterator.remove();
                        }
                    }
                    if (linha.isEmpty()) {
                        linhaIterator.remove();
                    }
                    
                }
                Iterator<Vertice> vertices = listaVerticesImpares.iterator();
                while(vertices.hasNext()){
                    Vertice v = vertices.next();
                    if(v.getId().equals(temp.getIdVertice()) || v.getId().equals(temp.getIdVerticeAdj())){
                        vertices.remove();
                    }
                }
                // System.out.println(this.toString());
            }
            

            return lista;
        }

        private void gerarMatriz() {

            for (Vertice v : listaVerticesImpares) {

                for (VetorRotasDijkstra vetor : vetoresRotaDijkstras) {

                    if (vetor.getId().equals(v.getId())) {

                        ArrayList<CelulaMatrizCusto> linha = new ArrayList<>();

                        for (Vertice ve : listaVerticesImpares) {
                            CelulaMatrizCusto c = new CelulaMatrizCusto(
                                    v.getId(), ve.getId(), vetor.getDistancia(ve.getId()));
                            linha.add(c);
                        }
                        matriz.add(linha);
                    }
                }

            }
        }

        @Override
        public String toString() {
            ArrayList<ArrayList<CelulaMatrizCusto>> matriz = this.matriz;

            StringBuilder str = new StringBuilder();

            str.append(String.format("%-3s", " "));
            for (Vertice v : listaVerticesImpares) {
                str.append(String.format("%-5s", v.getId()));
            }
            str.append("\n");
            for (ArrayList<CelulaMatrizCusto> linha : matriz) {
                str.append(String.format("%-3s", linha.get(0).getIdVertice()));
                for (CelulaMatrizCusto c : linha) {
                    str.append(String.format("%-5s", c.getCusto()));
                }
                str.append("\n");
            }

            return str.toString();

        }

    }

