package Modelo;

import Servico.*;

public class Camada {

    private Neuronio[] neuronios;
    private double[] saida;
    private FuncaoDeAtivacao funcao;
    private double[] erros = null;

    /**
     * Construtor da camada.
     *
     * @param n número de neurônios na camada
     * @param conexoes número de conexões por neurônio
     */
    Camada(int qtd_neuronios, int conexoes, int funcao) {



        neuronios = new Neuronio[qtd_neuronios];
        saida = new double[qtd_neuronios];

        //novo
        erros = new double[qtd_neuronios];

        for (int i = 0; i < neuronios.length; i++) {
            neuronios[i] = new Neuronio(conexoes);

            // Iniciando cada neurônio com valores aleatórios
            neuronios[i].inicializarPesos();

            //novo
            erros[i] = 0;
        }
        switch (funcao) {
            case 0:
                this.funcao = new Sigmoide();
                break;
            case 1:
                this.funcao = new TangenteHiperbolica();
                break;
        }

    }

    public void setErro(int i, double valor) {
        erros[i] = valor;
    }

    public double getErro(int i) {
        return erros[i];
    }

    void computar(double[] input) {
        for (int i = 0; i < neuronios.length; i++) {
            saida[i] = funcao.funcao(net(i, input));
        }
    }

    //retorna o numero de neuronios da camada
    public int getTamanho() {
        return neuronios.length;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + neuronios.length + "]";
    }

    //Retorna uma saida especifica
    public final double getSaida(int i) {
        return saida[i];
    }

    //Retorna o vetor com todas as saidas
    public final double[] getSaida() {
        return saida;
    }

    //Retorna um neuronio especifico
    public final Neuronio getNeuronio(int i) {
        return neuronios[i];
    }

    //Retorna o numero de conexoes/pesos do neuronio
    final public int getTamanhoNeuronio() {
        return neuronios[0].getTamanho();
    }

    //Realiza a ativacao dos neuronios
    private double net(int neuronio, double[] entrada) {
        double u = 0;

        for (int i = 0; i < entrada.length; i++) {
            u += entrada[i] * neuronios[neuronio].getPeso(i);
        }
        u -= neuronios[neuronio].getBias();
        return u;
    }

    public FuncaoDeAtivacao getFuncao() {
        return funcao;
    }
}
