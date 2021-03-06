
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
            FileReader fileReader = new FileReader(arq);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha = "";

            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
            }

            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
