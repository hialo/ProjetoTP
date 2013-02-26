
package Servico;

public class Sigmoide implements FuncaoDeAtivacao {
   
    //Sigmoide
    @Override
    public double funcao(double x) {
        return (1 / (1 + Math.exp(-x)));
    }
    
    //Derivada da sigmoide
    @Override
    public double derivada(double x) {
        return x * (1 - x);
    }
}
