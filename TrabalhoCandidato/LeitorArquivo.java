package TrabalhoCandidato;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import TrabalhoCandidato.Excecoes.ArquivoInvalidoException;

public class LeitorArquivo {

	public static void carregarCandidatos(String caminhoArquivo, TADCandidato sistema) {

		if (sistema == null) {
			throw new NullPointerException("Lista de candidatos nula");
		}

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(caminhoArquivo), "UTF-8"))) {

			String linha = proximaLinha(br);

			if (linha == null) {
				throw new ArquivoInvalidoException("Número de candidatos ausente");
			}

			int totalCandidatos = Integer.parseInt(linha);

			if (totalCandidatos < 0) {
				throw new ArquivoInvalidoException("Número inválido de candidatos (valor negativo)");
			}

			for (int i = 0; i < totalCandidatos; i++) {
				linha = proximaLinha(br);

				// pula o separador "****" que vem antes de cada candidato
				if (linha != null && linha.startsWith("****")) {
					linha = proximaLinha(br);
				}

				if (linha == null) {
					throw new ArquivoInvalidoException("Nome do candidato ausente");
				}
				
				String nome = linha;

				String estado = proximaLinha(br);
				if (estado == null) {
					throw new ArquivoInvalidoException("Estado do candidato ausente");
				}

				String sexoStr = proximaLinha(br);
				if (sexoStr == null) {
					throw new ArquivoInvalidoException("Sexo do candidato ausente");
				}

				String sexoInteresseStr = proximaLinha(br);
				if (sexoInteresseStr == null) {
					throw new ArquivoInvalidoException("Sexo de interesse ausente");
				}

				SexoCandidato sexo = converterSexo(sexoStr);
				SexoCandidato sexoInteresse = converterSexo(sexoInteresseStr);

				// o sexo do candidato é só F ou M; "I" vale apenas para o sexo de interesse
				if (sexo == null || sexo == SexoCandidato.Indiferente || sexoInteresse == null) {
					throw new ArquivoInvalidoException("Sexo ou sexo de interesse inválido");
				}

				String qtdStr = proximaLinha(br);
				if (qtdStr == null) {
					throw new ArquivoInvalidoException("Quantidade de áreas de interesse ausente");
				}

				int qtdAreas = Integer.parseInt(qtdStr);

				if (qtdAreas < 0) {
					throw new ArquivoInvalidoException("Quantidade de áreas de interesse inválida (valor negativo)");
				}

				long idCandidato = sistema.add(nome, estado, sexo, sexoInteresse);

				for (int j = 0; j < qtdAreas; j++) {

					String areaStr = proximaLinha(br);
					if (areaStr == null) {
						throw new ArquivoInvalidoException("Área de interesse ausente");
					}

					int area = Integer.parseInt(areaStr);

					if (area < 1 || area > 8) {
						throw new ArquivoInvalidoException("Área de interesse fora do intervalo de 1 a 8");
					}

					sistema.adicionaInteresse(idCandidato, area);
				}
			}

			System.out.println("Candidatos carregados com sucesso do arquivo: " + caminhoArquivo);

		} catch (FileNotFoundException e) {
			throw new ArquivoInvalidoException("arquivo não encontrado");

		} catch (IOException e) {
			throw new ArquivoInvalidoException("erro de leitura do arquivo");

		} catch (IllegalArgumentException | NullPointerException e) {
			throw new ArquivoInvalidoException("número em formato inválido (NumberFormatException) ou dados inválidos ao cadastrar candidato");
		}
	}

	// devolve a próxima linha não vazia (sem espaços nas pontas) ou null se o
	// arquivo acabou
	private static String proximaLinha(BufferedReader br) throws IOException {
		String linha = br.readLine();

		while (linha != null && linha.trim().isEmpty()) {
			linha = br.readLine();
		}

		if (linha == null) {
			return null;
		}

		return linha.trim();
	}

	private static SexoCandidato converterSexo(String sigla) {

		if (sigla.equalsIgnoreCase("F")) {
			return SexoCandidato.Feminino;

		} else if (sigla.equalsIgnoreCase("M")) {
			return SexoCandidato.Masculino;

		} else if (sigla.equalsIgnoreCase("I")) {
			return SexoCandidato.Indiferente;
		}

		return null;
	}
}