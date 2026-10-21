package TrabalhoCandidato;

public class TADCandidato {
	private Candidato head;
	private Candidato tail;

	private long proximoId;

	private int grauInteresseDesejado;

	private class Candidato {
		private long id;
		String nome;
		String estado;
		SexoCandidato sexo;
		SexoCandidato sexoPotencialC;
		Candidato prox;
		TADListaIdentificadores listaIds;
		TADListaInteresses interesses;

		public Candidato(String nome, String estado, SexoCandidato sexo, SexoCandidato sexoPotencialC) {
			this.id = proximoId++;
			this.nome = nome;
			this.estado = estado;
			this.sexo = sexo;
			this.sexoPotencialC = sexoPotencialC;
			listaIds = new TADListaIdentificadores();
			interesses = new TADListaInteresses();
		}

		public long getId() {
			return id;
		}

	}

	public TADCandidato() {
		head = new Candidato(null, null, null, null);
		head.id = -1;
		proximoId--;
		tail = head;
	}

	public long add(String nome, String estado, SexoCandidato sexo, SexoCandidato sexoPotencialC) {
		if (nome != null && !nome.trim().isEmpty() && estado != null && estado.trim().matches("[A-Za-z]{2}")
				&& sexo != null && sexoPotencialC != null) {
			tail.prox = new Candidato(nome, estado.trim().toUpperCase(), sexo, sexoPotencialC);
			tail = tail.prox;

			return tail.getId();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void inserePotencialCompanheiro(Candidato c1, Candidato c2) {
		if(c1 == null || c2 == null) {
			throw new NullPointerException("Candidato nulo");
		}

		if (verificaPotencialCompanheiro(c1, c2) && !c1.listaIds.contem(c2.getId())) {
			c1.listaIds.add(c2.getId());
		}
	}

	public int numeroCompanheiros(Candidato c) {
		if (c != null && c.listaIds != null) {
			return c.listaIds.getTamanho();
		} else {
			throw new NullPointerException();
		}
	}

	public boolean temInteresse(Candidato c, int id) {
		if (c != null && id >= 1) {
			return c.listaIds.verificarId(id);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public boolean reside(Candidato u, String sigla) {
		if (u != null && u.estado != null && sigla != null && sigla.length() >= 2) {
			if (u.estado.trim().equalsIgnoreCase(sigla.trim())) {
				return true;
			}
		}
		return false;
	}

	public void setGrauInteresseDesejado(int grau) {
		if (grau >= 0 || grau > 15) {
			this.grauInteresseDesejado = grau;
		} else {
			throw new IllegalArgumentException("Grau de interesse inválido, valor fora do intervalo de 0 a 15");
		}
	}

	public int getGrauInteresseDesejado() {
		return grauInteresseDesejado;
	}

	private boolean aceitaSexo(SexoCandidato preferencia, SexoCandidato sexoOutro) {
		return preferencia == SexoCandidato.Indiferente || preferencia == sexoOutro;
	}

	public boolean verificaPotencialCompanheiro(Candidato c1, Candidato c2) {

		if (c1 == null || c2 == null || c1 == c2) {
			throw new NullPointerException();
		}

		// verificar isso, pois não sei se para estar na lista de potencial companheiro,
		// precisa considerar o sexo de interesse dos dois ou somente o do candidato c1
		if (!reside(c1, c2.estado) || (!aceitaSexo(c1.sexoPotencialC, c2.sexo) || !aceitaSexo(c2.sexoPotencialC, c1.sexo))) {
			return false;
		}
		
		int grauComum = c1.interesses.somaPesosComuns(c2.interesses);

		return grauComum >= grauInteresseDesejado;
	}

	private Candidato buscarPorId(long id) {
		Candidato aux = head.prox;

		while (aux != null) {
			if (aux.getId() == id) {
				return aux;
			}

			aux = aux.prox;
		}

		return null;
	}

	public boolean adicionaInteresse(long idCandidato, int area) {
		Candidato c = buscarPorId(idCandidato);

		if (c == null || area < 1 || area > 8) {
			return false;
		}

		if (c.interesses.contem(area)) {
			return false;
		}

		c.interesses.add(area);
		return true;
	}

	public boolean possuiAreaInteresse(long idCandidato, int area) {
		Candidato c = buscarPorId(idCandidato);

		return c != null && c.interesses.contem(area);
	}

	// alterar depois, criado para fazer testes
	public void imprimirCandidatos() {
		Candidato aux = head.prox;

		while (aux != null) {
			System.out.println("ID: " + aux.getId() + " | Nome: " + aux.nome + " | Estado: " + aux.estado + " | Sexo: "
					+ aux.sexo);

			aux = aux.prox;
		}
	}

	public TADListaIdentificadores listaCompanheiros(Candidato c) {
		if (c != null) {
			return c.listaIds;
		} else {
			throw new NullPointerException("Candidato nulo");
		}

	}

	public void limparCompanheiros() {
		Candidato aux = head.prox;
		while (aux != null) {
			aux.listaIds = new TADListaIdentificadores();
			aux = aux.prox;
		}
	}

	public void processarTodosPares() {
		limparCompanheiros();
		Candidato u = head.prox;
		while (u != null) {
			Candidato v = head.prox;
			while (v != null) {
				if (u != v && verificaPotencialCompanheiro(u, v)) {
					inserePotencialCompanheiro(u, v);
				}
				v = v.prox;
			}
			u = u.prox;
		}
	}
	
	public TADListaIdentificadores possuemMaisCompanheiros(int k) {
		if (k < 1) {
			throw new IllegalArgumentException("K deve ser pelo menos 1");
		}

		TADListaIdentificadores resultado = new TADListaIdentificadores();

		for (int i = 0; i < k; i++) {
			Candidato melhor = null;
			Candidato aux = head.prox;

			while (aux != null) {
				// só considera quem ainda não foi escolhido
				if (!resultado.contem(aux.getId())) {
					if (melhor == null || numeroCompanheiros(aux) > numeroCompanheiros(melhor)) {
						melhor = aux;
					}
				}
				aux = aux.prox;
			}

			if (melhor == null) {
				break; // K maior que o total de candidatos
			}

			resultado.add(melhor.getId());
		}

		return resultado;
	}
	
	public String getNome(long id) {
		Candidato c = buscarPorId(id);
		if (c == null) {
			throw new IllegalArgumentException("Candidato inexistente");
		}
		return c.nome;
	}

	public int numeroCompanheiros(int id) {
		return numeroCompanheiros(buscarPorId(id));
	}

	public TADListaIdentificadores listaCompanheiros(long id) {
		Candidato c = buscarPorId(id);

		if (c == null) {
			throw new IllegalArgumentException("Candidato inexistente");
		}

		return c.listaIds;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ \n");
		Candidato atual = head.prox;

		while (atual != null) {
			sb.append(atual.id);
			sb.append(" - ");
			sb.append(atual.nome);
			sb.append("\n");
			atual = atual.prox;
		}
		sb.append("]");
		return sb.toString();
	}
}