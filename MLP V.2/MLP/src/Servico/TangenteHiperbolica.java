/*
 This class implements the hyperbolic tangent activation function, and it's derivative.
 */

package Servico;

/* Variables declaration:
 * x = The parameter of the hyperbolic tangent function. 
 */

public class TangenteHiperbolica implements FuncaoDeAtivacao {

    @Override
    public double funcao(double x) {
        return (Math.exp(x) - Math.exp(-x))/(Math.exp(x)+Math.exp(-x));
    }

    @Override
    public double derivada(double x) {
        return (4*Math.exp(2*x))/(Math.pow((Math.exp(2*x)+1),2));
    }
    
}
