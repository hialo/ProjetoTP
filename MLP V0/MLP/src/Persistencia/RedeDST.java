package Persistencia;

import Modelo.MLP;

public class RedeDST {

    public static MLP dst;

    public RedeDST(int funcao) {
        dst = new MLP(2);
        //adicionando a camada intermediaria pois a camada de entrada possui 15 entradas
        dst.adicionarPrimeiraCamada(5, 15, funcao);
        //adicionando a camada após a intermediária, vulgo camada de saída
        dst.adicionarCamada(6, funcao);
    }
}
