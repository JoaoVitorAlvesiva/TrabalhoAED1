package TrabalhoCandidato;

public class Main {
	public static void main(String[] args) {
		TADCandidato candidatos = new TADCandidato();
		
		// 1. Inserindo os candidatos
        int idAna = candidatos.add(
            "Ana", "MG",
            SexoCandidato.Feminino,
            SexoCandidato.Masculino
        );

        int idJoao = candidatos.add(
            "Joao", "MG",
            SexoCandidato.Masculino,
            SexoCandidato.Feminino
        );

        int idMaria = candidatos.add(
            "Maria", "SP",
            SexoCandidato.Feminino,
            SexoCandidato.Indiferente
        );

        System.out.println("IDs retornados:");
        System.out.println("Ana: " + idAna);
        System.out.println("Joao: " + idJoao);
        System.out.println("Maria: " + idMaria);

        // 2. Adicionando interesses a cada candidato
        candidatos.adicionaInteresse(idAna, 1);
        candidatos.adicionaInteresse(idAna, 5);
        candidatos.adicionaInteresse(idAna, 8);

        candidatos.adicionaInteresse(idJoao, 1);
        candidatos.adicionaInteresse(idJoao, 3);

        candidatos.adicionaInteresse(idMaria, 2);
        candidatos.adicionaInteresse(idMaria, 6);

        // 3. Conferindo os interesses
        System.out.println("\nInteresses cadastrados:");

        System.out.println("Ana gosta da area 1? "
            + candidatos.possuiAreaInteresse(idAna, 1));

        System.out.println("Ana gosta da area 5? "
            + candidatos.possuiAreaInteresse(idAna, 5));

        System.out.println("Ana gosta da area 2? "
            + candidatos.possuiAreaInteresse(idAna, 2));

        System.out.println("Joao gosta da area 3? "
            + candidatos.possuiAreaInteresse(idJoao, 3));

        System.out.println("Maria gosta da area 6? "
            + candidatos.possuiAreaInteresse(idMaria, 6));

        System.out.println("\nCandidatos cadastrados:");
        candidatos.imprimirCandidatos();
	}
}