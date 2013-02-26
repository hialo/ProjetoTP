package Modelo;

public class MLP {
    
    /* Variables declaration:
     * camadas = A vector of objects of type Camada. Receives the quantity of the layers of the network.
     * ultimacamada = A integer who has the value of the last layer of the network.
     */

    private Camada[] camadas;
    private int ultimaCamada = -1;
    //novo
    private double eta = 0.2;   //aprendizagem
    private double alpha = 0.8;

    /**
     * @param layers number of layers
     */
    public MLP(int camadas) {
        this.camadas = new Camada[camadas];
    }

    /**
     * @param neuronios numero de neuronios na camada
     * @param conexoes numero de entradas em cada neuronio
     */
    public void adicionarPrimeiraCamada(int neuronios, int conexoes, int funcao) {
        camadas[0] = new Camada(neuronios, conexoes, funcao);
        ultimaCamada = 0;
    }

    public void adicionarCamada(int neuronios, int funcao) {
        camadas[ultimaCamada + 1] = new Camada(neuronios, camadas[ultimaCamada].getTamanho(), funcao);
        ultimaCamada++;
    }

    /**
     * Forward
     *
     * @param input entrada da rede
     */
    public void processar(double[] entrada) {
        for (int i = 0; i < camadas.length; i++) {
            camadas[i].computar(entrada);

            entrada = camadas[i].getSaida();
        }
    }

    //Retorna a saida de um neuronio especifico na camada de saida
    public double getSaida(int neuronio) {
        return camadas[ultimaCamada].getSaida(neuronio);
    }

    //Retorna o numero de saidas
    public int getTamanhoDaSaida() {
        return camadas[ultimaCamada].getTamanho();
    }

    //Retorna o numero de camadas da MLP
    public int getTamanho() {
        return camadas.length;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + camadas.length + "]";
    }

    //Retorna uma camada especifica
    public Camada getCamada(int i) {
        return camadas[i];
    }

    //Retorna a ultima camada
    public Camada getUltimaCamada() {
        return camadas[ultimaCamada];
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

    public double getEta() {
        return eta;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getAlpha() {
        return alpha;
    }

    //Retropropagacao dos erros
    public void retropropagacao(double[] entrada, double[] saidaDesejada) {

        computarErrosDaUltimaCamada(saidaDesejada);
        computarErrosDasCamadasOcultas();
        computarDeltas(entrada);

        aplicarCorrecoes();

    }

    //Treinar rede
    public double treinar(double[] entrada, double[] saidaDesejada) {

        processar(entrada);
        retropropagacao(entrada, saidaDesejada);

        return computarErroFinal(entrada, saidaDesejada);
    }

    //Computa os erros da camada de saida
    private void computarErrosDaUltimaCamada(double[] saidaDesejada) {

        double erro = 0;
        Camada l = camadas[ultimaCamada];

        int tamanho = l.getTamanho();

        for (int i = 0; i < tamanho; i++) {
            erro = (saidaDesejada[i] - l.getSaida(i)) * camadas[ultimaCamada].getFuncao().derivada(l.getSaida(i));
            l.setErro(i, erro);
        }
    }

    //Computa erros das camadas internas
    private void computarErrosDasCamadasOcultas() {
        double error;

        for (int i = camadas.length - 2; i >= 0; i--) // Camada por camada 
        {
            for (int j = 0; j < camadas[i].getTamanho(); j++) { // Neuronio por neuronio
                error = 0;
                // Neuronio por neuronio na proxima camada
                for (int k = 0; k < camadas[i + 1].getTamanho(); k++) {
                    error += camadas[i + 1].getErro(k) * camadas[i + 1].getNeuronio(k).getPeso(j);
                }

                error *= camadas[i].getFuncao().derivada(getCamada(i).getSaida(j));
                camadas[i].setErro(j, error);
            }
        }
    }

    private void computarDeltas(double[] entrada) {
        int i, j, k;

        for (i = 0; i < camadas.length; i++) { // Layer by Layer
            for (j = 0; j < camadas[i].getTamanho(); j++) { // Neuron by Neuron
                for (k = 0; k < camadas[i].getNeuronio(j).getTamanho(); k++) { // Weight by Weight
                    camadas[i].getNeuronio(j).setDeltaw(k, eta * entrada[k] * camadas[i].getErro(j) + 
                            alpha * camadas[i].getNeuronio(j).getDeltaw(k));
                }
                camadas[i].getNeuronio(j).setDeltaBias(eta * (-1) * camadas[i].getErro(j) + alpha * camadas[i].getNeuronio(j).getDeltaBias());
            }
            entrada = camadas[i].getSaida();
        }
    }

    private void aplicarCorrecoes() {
        int i, j, k;

        for (i = 0; i < camadas.length; i++) {
            for (j = 0; j < camadas[i].getTamanho(); j++) {
                Neuronio n = camadas[i].getNeuronio(j);

                for (k = 0; k < n.getTamanho(); k++) {
                    n.corrigirPeso(k, camadas[i].getNeuronio(j).getDeltaw(k));
                }
                n.corrigirBias(camadas[i].getNeuronio(j).getDeltaBias());
            }
        }
    }

    private double computarErroFinal(double[] entrada, double[] saidaDesejada) {
        processar(entrada);

        double erro = 0;

        Camada l = camadas[ultimaCamada];

        int tamanho = l.getTamanho();

        for (int i = 0; i < tamanho; i++) {
            erro = (saidaDesejada[i] - l.getSaida(i));
        }
        if (erro < 0) {
            return erro * (-1);
        } else {
            return erro;
        }
    }
}
