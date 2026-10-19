package TrabalhoCandidato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LeitorArquivo {

	public static void carregarCandidatos(String caminhoArquivo, TADCandidato sistema) {

		if (sistema == null) {
			// voltar exceção: sistema de candidatos nulo
			return;
		}

		File arquivo = new File(caminhoArquivo);

		try (Scanner scanner = new Scanner(arquivo)) {

			if (!scanner.hasNextInt()) {
				// voltar exceção: número de candidatos inválido
				return;
			}

			int totalCandidatos = scanner.nextInt();

			if (totalCandidatos < 0) {
				// voltar exceção: número de candidatos negativo
				return;
			}

			for (int i = 0; i < totalCandidatos; i++) {

				if (scanner.hasNext("\\*\\*\\*\\*")) {
					scanner.next();
				}

				if (!scanner.hasNext()) {
					// voltar exceção: nome do candidato ausente
					return;
				}
				String nome = scanner.next();

				if (!scanner.hasNext()) {
					// voltar exceção: estado do candidato ausente
					return;
				}
				String estado = scanner.next();

				if (!scanner.hasNext()) {
					// voltar exceção: sexo do candidato ausente
					return;
				}
				String sexoStr = scanner.next();

				if (!scanner.hasNext()) {
					// voltar exceção: sexo de interesse ausente
					return;
				}
				String sexoInteresseStr = scanner.next();

				SexoCandidato sexo = converterSexo(sexoStr);
				SexoCandidato sexoInteresse = converterSexo(sexoInteresseStr);

				if (sexo == null || sexoInteresse == null) {
					// voltar exceção: sexo ou sexo de interesse inválido
					return;
				}

				if (!scanner.hasNextInt()) {
					// voltar exceção: quantidade de áreas inválida
					return;
				}

				int qtdAreas = scanner.nextInt();

				if (qtdAreas < 0) {
					// voltar exceção: quantidade de áreas negativa
					return;
				}

				int idCandidato = sistema.add(nome, estado, sexo, sexoInteresse);

				for (int j = 0; j < qtdAreas; j++) {

					if (!scanner.hasNextInt()) {
						// voltar exceção: área de interesse inválida
						return;
					}

					int area = scanner.nextInt();

					if (area < 1 || area > 8) {
						// voltar exceção: área de interesse fora do intervalo de 1 a 8
						return;
					}

					sistema.adicionaInteresse(idCandidato, area);
				}
			}

			System.out.println("Candidatos carregados com sucesso do arquivo: " + caminhoArquivo);

		} catch (FileNotFoundException e) {
			// voltar exceção: arquivo não encontrado

		} catch (InputMismatchException e) {
			// voltar exceção: formato dos dados incompatível com o esperado

		} catch (IllegalArgumentException | NullPointerException e) {
			// voltar exceção: dados inválidos ao cadastrar candidato
		}
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