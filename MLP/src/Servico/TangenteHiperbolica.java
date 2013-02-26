
package Servico;

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
