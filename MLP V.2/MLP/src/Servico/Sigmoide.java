/*
 This class implements the sigmoid activation function, and it's derivative.
 */

package Servico;

/* Variables declaration:
 * x = The parameter of the function.
 */

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
