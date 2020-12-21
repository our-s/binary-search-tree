import java.io.*;
import java.util.*;

public class BuscaIngenua {
    protected final int MAX = 1000000;
    protected String[] arranjo;
    int size = 0;

    BuscaIngenua() {
        arranjo = new String[MAX];
    }

    void insert(String s) {
        arranjo [size] = s;
        size++;
    }

    boolean find(String a) {
        for (int i = 0; i < size; i++) {
            if (arranjo[i].equals(a)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < size; i++) {
            ret.concat(arranjo[i]);
            ret.concat(", ");
        }
        return ret;
    }

    public void sort() {
        Arrays.sort(arranjo, 0, size);
    }

    public void clear() {
        size = 0;
    }

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

    public static void main (String[] args) {
        try {
            // Inserir o caminho do arquivo log
            String dirArqLog = "saidas/log_EP.txt";

            // inserir o caminho do arquivo de tempo de processamento
            String dirArqTempo = "saidas/tempo_EP.csv";

            // inserir o caminho da pasta que contem os arquivos de entrada
            //String dirPastaEntradas = "C:\\Users\\";
            String dirPastaEntradas = "entradas";
            String dirPastaEntradas2 = "verificar";

            BufferedWriter log = escritor(dirArqLog);
            BufferedWriter tempo = escritor(dirArqTempo);
            tempo.write("Nome do Arquivo;Tempo de Processamento(ms);Quantidade de Linhas"); // cabecalho do arquivo dos tempos de processamento
            tempo.newLine();

            File directoryPath = new File(dirPastaEntradas);
            String[] contents = directoryPath.list(); // lista com todos os arquivos existentes na pasta de entradas (tarefas)

            File directoryPath2 = new File(dirPastaEntradas2); // lista com todos os arquivos existentes na pasta de verificar (tarefas)
            String[] contents2 = directoryPath2.list();

            BufferedReader leitor, leitor2;
            String str, str2;

            BuscaIngenua lista = new BuscaIngenua(); // de entrada
            BuscaIngenua lista2 = new BuscaIngenua(); // de verificar
            ArrayList<String> listaComum = new ArrayList(); // elementos em comum

            for (int i = 0; i < contents.length; i++) { // para percorrer os arquivos da pasta

                System.out.println("Processando Arquivo: " + contents[i]);
                System.out.println("Processando Arquivo: " + contents2[i]);
                log.write("Processando Arquivos: " + contents[i]);
                log.newLine();
                log.write("Processando Arquivos: " + contents2[i]);

                leitor = leitor(dirPastaEntradas + "/" + contents[i]);
                str = leitor.readLine();

                leitor2 = leitor(dirPastaEntradas2 + "/" + contents2[i]);
                str2 = leitor2.readLine();

                int qntLinhas = 0;
                long t = System.currentTimeMillis();

                while (str != null) {
                    qntLinhas++;

                    if (str.trim().equals("")) {
                        log.newLine();
                    } else {
                        lista.insert(str);
                        str = leitor.readLine();
                    }
                }
                while (str2 != null) {
                    qntLinhas++;

                    if (str2.trim().equals("")) {
                        log.newLine();
                    } else {
                        lista2.insert(str2); // add na lista2 criada
                        str2 = leitor2.readLine();
                    }
                }

                for (int j = 0; j < lista2.getSize(); j++) {
                    String verifica = lista.arranjo[j];

                    if (lista2.find(verifica)) {
                        //lista3.insert(verifica);
                        listaComum.add(verifica);
                    }
                }

                // imprime para cada arquivo combinado (tarefas e verificar) <número>.txt processado
                //System.out.println();

                t = System.currentTimeMillis() - t; // tempo
                tempo.write(contents[i] + ";" + t + ";" + qntLinhas);
                tempo.newLine();
                if (i != contents.length - 1) log.newLine();
                //lista.clear();
                //lista2.clear();
                leitor.close();
                }

//                log.close();
                tempo.close();

            Set<String> set = new HashSet<>(listaComum); // para sem repetido
            listaComum.clear();
            listaComum.addAll(set);

            // imprime somente no final tudo
            log.write("\n\nLista de Saída de Ambas (elementos em comum): \nNúmero de tarefas da lista: " + listaComum.size());
            log.write("\nTarefas: " + listaComum.toString());
            log.newLine();

            log.close();

            } catch(java.io.IOException e){
                System.out.println("Erro ao executar o programa (Possivelmente Arquivo de Entrada Corrompido): " + e.getMessage());
                e.printStackTrace();
            }
    }
}