
package TrabalhoCandidato;

public class TADListaInteresses {

    private Celula head;
    private Celula tail;
    private int size;

    private class Celula {
        private int id;
        private Celula prox;

        public Celula(int id) {
            this.id = id;
            this.prox = null;
        }

        public int getId() {
            return id;
        }
    }

    public TADListaInteresses() {
        head = new Celula(0);
        tail = head;
    }

    public void add(int id) {
        if (id >= 1 && id <= 8 && !contem(id)) {
            tail.prox = new Celula(id);
            tail = tail.prox;
            size++;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    public boolean contem(int id) {
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

    public int somaPesosComuns(TADListaInteresses outra) {
        if (outra == null) {
            return 0;
        }

        int soma = 0;
        Celula aux = head.prox;

        while (aux != null) {
            int area = aux.getId();

            if (outra.contem(area)) {
                soma += pesoDaArea(area);
            }

            aux = aux.prox;
        }

        return soma;
    }

    private int pesoDaArea(int id) {
        switch (id) {
            case 1: // Esportes
            case 3: // Música
            case 4: // Cinema
                return 1;

            case 2: // Artes
            case 6: // Animais
            case 7: // Gastronomia
                return 2;

            case 5: // Tecnologia
            case 8: // Ciências
                return 3;

            default:
                return 0;
        }
    }
}