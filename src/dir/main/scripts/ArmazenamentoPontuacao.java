package dir.main.scripts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArmazenamentoPontuacao {

    private static final String NOME_ARQUIVO = "src\\dir\\main\\scripts\\maior_pontuacao.txt";

    public static void armazenarMaiorPontuacao(int novaPontuacao) {
        try {
            // Lê a pontuação atual armazenada no arquivo
            int pontuacaoAtual = lerMaiorPontuacao();

            // Verifica se a nova pontuação é maior que a pontuação atual
            if (novaPontuacao > pontuacaoAtual) {
                // Atualiza a pontuação no arquivo
                escreverMaiorPontuacao(novaPontuacao);
                System.out.println("Nova maior pontuação registrada: " + novaPontuacao);
            } else {
            }
        } catch (IOException e) {
            System.err.println("Erro ao manipular o arquivo: " + e.getMessage());
        }
    }

    private static int lerMaiorPontuacao() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            // Se o arquivo estiver vazio, retorna 0
            String linha = reader.readLine();
            if (linha == null || linha.isEmpty()) {
                return 0;
            }

            // Converte a linha lida para inteiro e retorna
            return Integer.parseInt(linha);
        }
    }

    private static void escreverMaiorPontuacao(int pontuacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            // Escreve a nova pontuação no arquivo
            writer.write(Integer.toString(pontuacao));
            // Certifica-se de esvaziar o buffer para garantir que os dados sejam gravados no arquivo
            writer.flush();
        }
    }
}

