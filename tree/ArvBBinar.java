package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ArvBBinar {

    public static BufferedReader leitor(String path) {
        try {
            FileInputStream fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            return br;
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo " + path + " : " + e.getMessage());
            return null;
        }
    }
    public static BufferedWriter escritor(String path) {
        try {
            FileOutputStream fstream = new FileOutputStream(path);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fstream));
            return bw;
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo " + path + " : " + e.getMessage());
            return null;
        }
    }

    protected static class StringComparator implements Comparator<String> {
        public int compare(String v1, String v2) {
            return v1.compareTo(v2);
        }
    }

    public static void main (String args []) {
        try {
            // Inserir o caminho do arquivo log
//            //String dirArqLog = "C:\\Users\\";
            String dirArqLog = "./saidas/log_EP_Arv.txt";

            // inserir o caminho do arquivo de tempo de processamento
//            //String dirArqTempo = "C:\\Users\\";
            String dirArqTempo = "./saidas/tempo_EP_Arv.csv";

            // inserir o caminho da pasta que contem os arquivos de entrada
            //String dirPastaEntradas = "C:\\Users\\";
            String dirPastaEntradas = "./entradas";
            String dirPastaEntradas2 = "./verificar";

            BufferedWriter log = escritor(dirArqLog);
            BufferedWriter tempo = escritor(dirArqTempo);
            tempo.write("Nome do Arquivo;Tempo de Processamento(ms);Quantidade de Linhas"); // cabecalho do arquivo dos tempos de processamento
            tempo.newLine();

            File directoryPath = new File(dirPastaEntradas);
            String contents[] = directoryPath.list(); // lista com todos os arquivos existentes na pasta de entradas (tarefas)

            File directoryPath2 = new File(dirPastaEntradas2); // lista com todos os arquivos existentes na pasta de verificar (tarefas)
            String contents2[] = directoryPath2.list();

            BufferedReader leitor, leitor2;
            String str, str2;


            BinarySearchTree lista = new BinarySearchTree<String, String>(new StringComparator()); // de entrada
            ArrayList lista2 = new ArrayList(); // de verificar
            ArrayList<Entry<String, String>> listaComum = new ArrayList(); // elementos em comum

            log.write("Processando Arquivos: ");
            log.newLine();

            for (int i = 0; i < contents.length; i++) { // para percorrer os arquivos da pasta de entrada (tarefas)

                System.out.println("Processando Arquivo: " + contents[i]);
                System.out.println("Processando Arquivo: " + contents2[i]);
                log.write(contents[i] + " , " + contents2[i]);
                log.newLine();
//                    leitor = leitor(dirPastaEntradas + "\\" + contents[i]);
                leitor = leitor(dirPastaEntradas + "/" + contents[i]);
                str = leitor.readLine();

                leitor2 = leitor(dirPastaEntradas2 + "/" + contents2[i]);
                str2 = leitor2.readLine();

                int qntLinhas = 0;
                long t = System.currentTimeMillis();

                while (str != null) { // percorrer as linhas do arquivo considerando o arquivo verificar com mais numero de linhas
                    qntLinhas++;

                    if (str.trim().equals("")) {
                        log.newLine();
                    } else {
                        lista.insert(str, '0');
                        str = leitor.readLine();
                    }
                }
                while (str2 != null) {
                    qntLinhas++;

                        lista2.add(str2); // add na lista2 criada
                        str2 = leitor2.readLine();

                }

                for (int j = 0; j < lista2.size(); j++) {
                    Entry<String, String> verifica = lista.find(lista2.get(j));

                    if (verifica != null) {
                        listaComum.add(verifica);
                    }
                }

                t = System.currentTimeMillis() - t; // tempo

                tempo.write(contents[i] + ";" + t + ";" + qntLinhas);
                tempo.newLine();

                leitor.close();
            }
            tempo.close();

            HashSet<Entry<String, String>> set = new HashSet<Entry<String, String>>(listaComum); // para sem repetido
            listaComum.clear();
            listaComum.addAll(set);

//            // imprime somente no final tudo
//            // imprime lista de Entradas no arquivo de log
//            log.write("\n\nLista de Entrada de tarefas: \nNúmero de tarefas da lista: " + lista.size());
//            //log.write("\nTarefas: " + lista.toString());
//            log.newLine();
//
//            // imprime lista de Verificar no arquivo de log
//            log.write("\n\nLista de Verificar tarefas: \nNúmero de tarefas da lista: " + lista2.size());
//            log.newLine();

            // imprime lista de elementos em comum da lista de entradas e verificar
            log.write("\n\nLista de Saída de Ambas (elementos em comum): \nNúmero de tarefas da lista: " + listaComum.size());
            log.newLine();

            log.close();


        } catch(java.io.IOException e){
            System.out.println("Erro ao executar o programa (Possivelmente Arquivo de Entrada Corrompido): " + e.getMessage());
            e.printStackTrace();
        }
    }
}