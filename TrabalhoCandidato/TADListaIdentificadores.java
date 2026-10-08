package TrabalhoCandidato;

public class TADListaIdentificadores<Candidato> {
	private Candidato head;
	private Candidato tail;
	
	public class Candidato {
		int id;
		Candidato prox;
		
		public Candidato(int id) {
			this.id = id;
		}
	}

	public TADListaIdentificadores(Candidato head) {
		head = new Candidato(-1);
		tail = head;
	}
	
	public void add(int id) {
		if(id >= 0) {
			tail.prox = new Candidato(id);
			tail = tail.prox;
		}
	}
	
}