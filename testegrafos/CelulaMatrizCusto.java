// LUCAS FRITZKE E GUILHERME BACK

public class CelulaMatrizCusto {
    String idVertice;
        String idVerticeAdj;
        int custo;

        public CelulaMatrizCusto(String idVertice, String idVerticeAdj, int custo) {
            this.idVertice = idVertice;
            this.idVerticeAdj = idVerticeAdj;
            this.custo = custo;
        }

        public String getIdVertice() {
            return idVertice;
        }

        public void setIdVertice(String idVertice) {
            this.idVertice = idVertice;
        }

        public String getIdVerticeAdj() {
            return idVerticeAdj;
        }

        public void setIdVerticeAdj(String idVerticeAdj) {
            this.idVerticeAdj = idVerticeAdj;
        }

        public int getCusto() {
            return custo;
        }

        public void setCusto(int custo) {
            this.custo = custo;
        }

    }

