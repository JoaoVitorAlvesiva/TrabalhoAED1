package TrabalhoCandidato;

public class TADListaIdentificadores {
	private Celula head;
	private Celula tail;
	private int size;
	
	public class Celula {
		long id;
		Celula prox;
		
		public Celula(long id) {
			this.id = id;
			this.prox = null;
		}
		
		public long getId() {
			return id;
		}
	}
	

	public TADListaIdentificadores() {
		this.head = new Celula(0);
		this.tail = head;
	}
	
	public void add(long id) {
		if(id >= 0 && !contem(id)) {
			tail.prox = new Celula(id);
			tail = tail.prox;
			size++;
		}
	}
	
	public boolean contem(long id) {
        Celula aux = head.prox;

        while (aux != null) {
            if (aux.getId() == id) {
                return true;
            }
            aux = aux.prox;
        }

        return false;
    }
	
	public int getTamanho() {
		return size;
	}
	
	public Celula getPrimeiro() {
		return this.head.prox;
	}
	
	public boolean verificarId(int id) {
		Celula aux = head.prox;
		
		while(aux != null) {
			if(aux.getId() == id) {
				return true;
			}
			aux = aux.prox;
		}
		
		return false;
	}
	
}