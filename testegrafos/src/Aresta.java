// LUCAS FRITZKE E GUILHERME BACK 2

public class Aresta implements Comparable<Aresta> {

    private Vertice[] vertices = new Vertice[2];
    private int custo;
    private boolean duplicacao;
    private char flag = ' ';


    public char getFlagBusca() {
        return flagBusca;
    }

    public void setFlagBusca(char flagBusca) {
        this.flagBusca = flagBusca;
    }

    private char flagBusca;
    

    

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public boolean isDuplicacao() {
        return duplicacao;
    }

    public void setDuplicacao(boolean duplicacao) {
        this.duplicacao = duplicacao;
    }

    public Aresta() {
    }

    public Aresta(Vertice v1, Vertice v2, int custo){
        this.custo = custo;
        this.vertices[0] = v1;
        this.vertices[1] = v2;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public Vertice[] getVertices(){
        return this.vertices;
    }

    @Override
    public int compareTo(Aresta o) {
        Vertice[] vo = o.getVertices();
        if(this.vertices[0].getId().equals(vo[0].getId()) &&
            this.vertices[1].getId().equals(vo[1].getId()) && 
            this.getCusto() == o.getCusto()){
                return 0;
            } else if (this.getCusto() > o.getCusto()) {
                return 1;
            } else {
                return -1;
            }
    }

    public Vertice getVerticeAdjacente(Vertice u){
        Vertice[] v = this.getVertices();
        if(v[0].equals(u)){
            return v[1];
        } else{
            return v[0];
        } 

    }

    @Override
    public boolean equals(Object obj) {
        Aresta o = (Aresta) obj;
        int r = this.compareTo(o);
        if(r == 0){
            return true;
        } else {
           return false;
        }
        
    }
}
