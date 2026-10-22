package TrabalhoCandidato;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

// Gera o arquivo candidatos.txt com dados aleatórios.
// Os nomes vêm de nomes.txt, um por linha, no formato "Nome;Sexo" (ex.: Ana Souza;F).
public class GeradorCandidatos {

	public static void main(String[] args) throws IOException {
		int n = 50;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		// lê o dicionário de nomes e o sexo de cada um
		String[] nomes = new String[1000];
		String[] sexosDosNomes = new String[1000];
		int qtdNomes = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("nomes.txt"), "UTF-8"));
		String linha = br.readLine();
		while (linha != null && qtdNomes < nomes.length) {
			if (!linha.trim().isEmpty()) {
				String[] partes = linha.split(";");
				nomes[qtdNomes] = partes[0].trim();
				sexosDosNomes[qtdNomes] = partes[1].trim();
				qtdNomes++;
			}
			linha = br.readLine();
		}
		br.close();

		String[] estados = { "MG", "SP", "RJ", "ES" };
		String[] sexosInteresse = { "F", "M", "I" };
		Random random = new Random();

		PrintWriter pw = new PrintWriter("candidatos.txt", "UTF-8");
		pw.println(n);

		for (int i = 0; i < n; i++) {
			int pos = random.nextInt(qtdNomes);

			pw.println("****");
			pw.println(nomes[pos]);
			pw.println(estados[random.nextInt(estados.length)]);
			pw.println(sexosDosNomes[pos]);
			pw.println(sexosInteresse[random.nextInt(3)]);

			// de 1 a 5 áreas, sem repetir
			int qtdAreas = 1 + random.nextInt(5);
			pw.println(qtdAreas);

			boolean[] usada = new boolean[9];
			int sorteadas = 0;
			while (sorteadas < qtdAreas) {
				int area = 1 + random.nextInt(8);
				if (!usada[area]) {
					usada[area] = true;
					pw.println(area);
					sorteadas++;
				}
			}
		}

		pw.close();
		System.out.println(n + " candidatos gravados em candidatos.txt");
	}
}