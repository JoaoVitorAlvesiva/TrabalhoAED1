package TrabalhoCandidato;

import java.util.Scanner;

public class Main {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		TADCandidato candidatos = new TADCandidato();
		LeitorArquivo.carregarCandidatos("candidatos.txt", candidatos);

		int opcao;
		// início da interatividade com o usuário
		do {
			System.out.println("O que deseja fazer?");
			System.out.println("| 1 - Inserir/atualizar o grau de interesse desejado");
			System.out.println("| 2 - Imprimir a lista dos potenciais companheiros de um candidato");
			System.out.println("| 3 - Imprimir id/nome dos K candidatos com maior número de potenciais companheiros");
			System.out.println("| 4 - Imprimir id/nome dos candidatos para os quais nenhum companheiro foi encontrado");
			System.out.println("| 5 - Imprimir id/nome de candidatos com interesse em uma determinada área");
			System.out.println("| 6 - Imprimir id/nome de candidatos que residem em um determinado estado");
			System.out.println("| 0 - Sair do programa");
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
				System.out.println("Informe o id de qual dos candidatos abaixo você deseja saber os potenciais companheiros: ");
				System.out.println(candidatos);
				imprimirPotenciaisCompanheiros(candidatos, scan.nextInt());
				break;
			}
			case 3: {

				break;
			}
			case 4: {

				break;
			}
			case 5: {

				break;
			}
			case 6: {

				break;
			}
			default:
				System.out.println("Opção inválida, valor fora do intervalo de 0 a 6");
			}
		} while(opcao != 0);
	}

	public static void inserirGrauInteresse(TADCandidato candidatos) {
		System.out.println("Qual o grau de interesse desejado?");
		int grauInteresse = scan.nextInt();
		candidatos.setGrauInteresseDesejado(grauInteresse);
	}
	
	public static void imprimirPotenciaisCompanheiros(TADCandidato candidatos, int idCand) {
		TADListaIdentificadores lista = candidatos.listaCompanheiros(idCand);

		System.out.println("Candidato: " + idCand + " - " + candidatos.getNome(idCand));
		System.out.println("Número de potenciais companheiros: " + lista.getTamanho());

		TADListaIdentificadores.Celula c = lista.getPrimeiro();

		while (c != null) {
			long id = c.getId();
			System.out.println(id + " - " + candidatos.getNome(id));
			c = c.prox;
		}
	}
}