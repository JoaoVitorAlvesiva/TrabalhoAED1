package TrabalhoCandidato;

public class TADListaIdentificadores {
	private Celula head;
	private Celula tail;
	
	public class Celula {
		int id;
		Celula prox;
		
		public Celula(int id) {
			this.id = id;
			this.prox = null;
			
			
		}
		
		public int getId() {
			return id;
		}
	}
	

	public TADListaIdentificadores() {
		this.head = new Celula(-1);
		this.tail = head;
	}
	
	public void add(int id) {
		if(id >= 0) {
			tail.prox = new Celula(id);
			tail = tail.prox;
		}
	}
	
	public int getTamanho() {
		int contador = 0;
		Celula aux = this.head.prox;
		while(aux != null) {
			contador++;
			aux = aux.prox;
		}
		return contador;
	}
	
	public Celula getPrimeiro() {
		return this.head.prox;
	}
	
	
}