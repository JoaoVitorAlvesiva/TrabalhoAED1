package TrabalhoCandidato;

import java.util.InputMismatchException;
import java.util.Scanner;

import TrabalhoCandidato.Excecoes.ArquivoInvalidoException;

public class Main {
	private static Scanner scan = new Scanner(System.in);
	private static String[] areas = { "", "ESPORTES", "ARTES", "MÚSICA", "CINEMA",
			"TECNOLOGIA", "ANIMAIS", "GASTRONOMIA", "CIÊNCIAS" };
	private static String[] estados = {"MG", "SP", "RJ", "ES" };

	public static void main(String[] args) {
		TADCandidato candidatos = new TADCandidato();
		try {
			LeitorArquivo.carregarCandidatos("candidatos.txt", candidatos);
		} catch (ArquivoInvalidoException e) {
			System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
			return;
		}

		candidatos.processarTodosPares();
		
		int opcao = -1;
		// início da interatividade com o usuário
		do {
			System.out.println("\n-- MENU --");
			System.out.println("O que deseja fazer?");
			System.out.println("| 1 - Inserir/atualizar o grau de interesse desejado");
			System.out.println("| 2 - Imprimir a lista dos potenciais companheiros de um candidato");
			System.out.println("| 3 - Imprimir id/nome dos K candidatos com maior número de potenciais companheiros");
			System.out.println("| 4 - Imprimir id/nome dos candidatos para os quais nenhum companheiro foi encontrado");
			System.out.println("| 5 - Imprimir id/nome de candidatos com interesse em uma determinada área");
			System.out.println("| 6 - Imprimir id/nome de candidatos que residem em um determinado estado");
			System.out.println("| 0 - Sair do programa");

			try {
				opcao = scan.nextInt();

				switch (opcao) {
				case 0: {
					System.out.println("Programa encerrado!");
					break;
				}
				case 1: {
					inserirGrauInteresse(candidatos);
					candidatos.processarTodosPares();
					break;
				}
				case 2: {
					System.out.println(candidatos);
					System.out.println(
							"Informe o id de qual dos candidatos acima você deseja saber os potenciais companheiros: ");
					imprimirPotenciaisCompanheiros(candidatos, scan.nextInt());
					break;
				}
				case 3: {
					System.out.println(
							"Informe a quantidade de candidatos que você deseja saber que tem o maior número de potenciais companheiros: ");
					possuemMaisCompanheiros(candidatos, scan.nextInt());
					break;
				}
				case 4: {
					naoPossuiCompanheiros(candidatos);
					break;
				}
				case 5: {
					imprimirAreas();
					System.out.println("Informe o código da área de interesse: ");
					candidatosComInteresse(candidatos, scan.nextInt());
					break;
				}
				case 6: {
					imprimirEstados();
					System.out.println("Informe a sigla do estado que deseja: ");
					candidatosQueResidem(candidatos, scan.next());
					break;
				}
				default:
					System.out.println("Opção inválida, valor fora do intervalo de 0 a 6");
					break;
				}
			} catch (IllegalArgumentException | NullPointerException e) {
				System.err.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Digite um número inteiro.");
				scan.nextLine(); // descarta o texto digitado
				opcao = -1;
			}
		} while (opcao != 0);
	}

	public static void inserirGrauInteresse(TADCandidato candidatos) {
		System.out.println("Qual o grau de interesse desejado?");
		int grauInteresse = scan.nextInt();
		candidatos.setGrauInteresseDesejado(grauInteresse);
		System.out.println("Grau de interesse desejado atual: " + candidatos.getGrauInteresseDesejado());
	}

	public static void imprimirPotenciaisCompanheiros(TADCandidato candidatos, int idCand) {
		TADListaIdentificadores lista = candidatos.listaCompanheiros(idCand);

		System.out.println("Candidato: " + idCand + " - " + candidatos.getNome(idCand) + " - " + candidatos.getEstado(idCand));
		System.out.println("Número de potenciais companheiros: " + lista.getTamanho());

		System.out.println(candidatos.formataLista(lista));
	}

	public static void possuemMaisCompanheiros(TADCandidato candidatos, int k) {
		TADListaIdentificadores cands = candidatos.possuemMaisCompanheiros(k);

		System.out.println("-- CANDIDATOS COM MAIS COMPANHEIROS --");
		System.out.println(candidatos.formataLista(cands));
	}

	public static void naoPossuiCompanheiros(TADCandidato candidatos) {
		TADListaIdentificadores cands = candidatos.naoPossuiCompanheiros();

		System.out.println("-- CANDIDATOS SEM COMPANHEIROS ENCONTRADOS --");
		System.out.println(candidatos.formataLista(cands));
	}
	
	public static void imprimirAreas() {
		System.out.println("-- ÁREAS DE INTERESSE --");
		for (int i = 1; i < areas.length; i++) {
			System.out.println(i + " - " + areas[i]);
		}
	}
	
	public static void candidatosComInteresse(TADCandidato candidatos, int area) {
		TADListaIdentificadores cands = candidatos.candidatosComInteresse(area);

		System.out.println("-- CANDIDATOS COM INTERESSE EM " + area + " - " + areas[area] + " --");

		System.out.println(candidatos.formataLista(cands));

		System.out.println("Total: " + cands.getTamanho());
	}
	
	public static void imprimirEstados() {
		System.out.println("-- ESTADOS DISPONÍVEIS --");
		for (int i = 0; i < estados.length; i++) {
			System.out.println(estados[i]);
		}
	}
	
	public static void candidatosQueResidem(TADCandidato candidatos, String sigla) {
		TADListaIdentificadores cands = candidatos.candidatosQueResidem(sigla, estados);

		System.out.println("-- CANDIDATOS QUE MORAM EM " + sigla.toUpperCase() + " --");

		System.out.println(candidatos.formataLista(cands));

		System.out.println("Total: " + cands.getTamanho());
	}
}