package Modelo;

import java.util.Random;

public class Neuronio {

    private double[] pesos = null;
    private double bias = 0;
    private double[] deltaw = null;
    private double deltaBias = 0;

    Neuronio(int conexoes) {
        pesos = new double[conexoes];
        deltaw = new double[conexoes];

        //Iniciando com zeros
        for (int i = 0; i < deltaw.length; i++) {
            deltaw[i] = 0;
        }
    }

    final double getDeltaw(int i) {
        return deltaw[i];
    }

    final void setDeltaw(int i, double valor) {
        deltaw[i] = valor;
    }

    final double getDeltaBias() {
        return deltaBias;
    }

    final void setDeltaBias(double deltaBias) {
        this.deltaBias = deltaBias;
    }

    //Retorna a quantidade de pesos
    public int getTamanho() {
        return pesos.length;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        b.append(getClass().getName()).append("[");

        b.append("bias=").append(bias).append("; ");

        for (int i = 0; i < pesos.length; i++) {
            b.append("w").append(i + 1).append("=").append(pesos[i]).append("; ");
        }

        b.append("]");

        return b.toString();
    }

    public final double getPeso(int i) {
        return pesos[i];
    }

    final void setPeso(int i, double w) {
        pesos[i] = w;
    }

    final double getBias() {
        return bias;
    }

    final void setBias(double b) {
        bias = b;
    }

    //Funções de correção durante o treinamento
    public final void corrigirPeso(int i, double delta) {
        pesos[i] += delta;
    }

    public final void corrigirBias(double delta) {
        bias += delta;
    }
    
    //Inicializa os pesos com valores randomicos (o bias tbm)
    //Random rd = new Random();

    void inicializarPesos() {
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = Math.random(); //
                    //(Math.random() - 0.5) * 2;
        }
        bias = Math.random();
    }
}
