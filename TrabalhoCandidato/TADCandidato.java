package TrabalhoCandidato;

public class TADCandidato {
	private Candidato head;
	private Candidato tail;
	
	private class Candidato {
		private int id;
		String nome;
		String estado;
		SexoCandidato sexo;
		SexoCandidato sexoPotencialC;
		Candidato prox;
		TADListaIdentificadores listaIds;
		
		private static int geraId = 0;
		
		public Candidato(String nome, SexoCandidato sexo, SexoCandidato sexoPotencialC) {
			this.id = geraId++;
			this.nome = nome;
			this.sexo = sexo;
			this.sexoPotencialC = sexoPotencialC;
			listaIds = new TADListaIdentificadores<Celula>();
		}

		public int getId() {
			return id;
		}
		
	}
	
	public TADCandidato () {
		head = new Candidato(null, null, null);
		tail = head;
	}
	
	public void add(String nome, SexoCandidato sexo, SexoCandidato sexoPotencialC) {
		if(nome != null && sexo != null && sexoPotencialC != null) {
			tail.prox = new Candidato(nome, sexo, sexoPotencialC);
			tail = tail.prox;
		}
	}
	
	//fazer
	public boolean verificaPotencialCompanheiro(Candidato c) {
		return true;
	}
	
	//mexer ainda
	public void inserePotencialCompanheiro(Candidato c) {
		if(verificaPotencialCompanheiro(c)) {
			listaIds.add(c.getId());
		}
	}
	
	public boolean reside(Candidato u, String sigla) {
		if(u != null && u.estado != null && sigla != null && sigla.length() >= 2) {
			if(u.estado.trim().equalsIgnoreCase(sigla)) {
				return true;
			}
		}
		return false;
	}
}