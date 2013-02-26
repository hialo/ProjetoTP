
package Servico;

import java.io.*;

public class UtilArquivos {

    public static void escrever(File dir, File arq, String conteudo, boolean sobreescrever) throws IOException {
        FileWriter fileWriter = new FileWriter(arq, sobreescrever);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(conteudo);

        printWriter.flush();
        printWriter.close();
    }

    public static void ler(File dir, File arq) {
        try {
            //Indicamos o arquivo que será lido
            FileReader fileReader = new FileReader(arq);

            //Criamos o objeto bufferReader que nos
            //oferece o método de leitura readLine()
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //String que irá receber cada linha do arquivo
            String linha = "";

            //Fazemos um loop linha a linha no arquivo,
            // enquanto ele seja diferente de null.
            //O método readLine() devolve a linha na
            // posicao do loop para a variavel linha.
            while ((linha = bufferedReader.readLine()) != null) {
                //Aqui imprimimos a linha
                System.out.println(linha);
            }

            //liberamos o fluxo dos objetos ou fechamos o arquivo
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
