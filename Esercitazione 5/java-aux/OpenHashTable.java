import java.util.LinkedList;

public class OpenHashTable extends AbstractHashTable {
	// Un array di Entry per la tabella
	private Entry[] table;
	// marcatore di cella vuota ma da esaminare durante probing
	private final Entry DEFUNCT = new Entry(null, 0);

	// Costruttori
	public OpenHashTable(int cap, int p, double lambda) {
		super(cap, p, lambda);
	}
	public OpenHashTable(int cap, int p) {
		super(cap, p); // massimo fattore di carico predefinito
	}
	public OpenHashTable(int cap) {
		super(cap); // primo predefinito
	}
	public OpenHashTable() {
		super(); // capacità predefinita
	}

	// Metodi non implementati in AbstractHashTable

	// Inizializza una tabella hash vuota secondo i parametri passati al costruttore
	protected void createTable() {
		table = new Entry[this.getCapacity()];
	}

	// Restituisce il valore (intero) associato alla chiave k
	// Restituisce -1 se la chiave è assente
	public int get(String k) {
		int h = hashFunction(k);
		int i = findSlot(h, k);
		if (i < 0)
			return -1;
		return table[i].getValue();
	}

	// Aggiorna il valore associato alla chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int put(String k, int value) {
		int h = hashFunction(k);
		int i = findSlot(h, k);

        if (i == 0 && !isDisp(i) || i > 0) {    //oppure if (i >= 0)
			int vecchio = table[i].getValue();
			table[i].setValue(value);
			return vecchio;
		}

		table[-i] = new Entry(k, value);        //oppure table[-(i+1)]
		this.incSize();

		double lambda = (double) size() / (double) getCapacity();

		if (lambda >= getMaxLambda()) {
            System.out.println("lambda: " + lambda);
			int newCap = 2 * getCapacity() + 1;
            while(!isPrimo(newCap))
                newCap += 2;
			resize(newCap);
		}
		return -1;
	}

	// Rimuove la coppia con chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int remove(String k) {
		int h = hashFunction(k);
		int i = findSlot(h, k);
		if (i < 0)
			return -1;
		int vecchio = table[i].getValue();
		table[i] = DEFUNCT;
		this.decSize();
		return vecchio;
	}

	// Restituisce un oggetto Iterable contenente tutte le coppie presenti
	// nella tabella hash
	public Iterable<Entry> entrySet() {
		LinkedList<Entry> entries = new LinkedList<Entry>();
		for (int i = 0; i < this.getCapacity(); i++) {
			if (!isDisp(i))
				entries.add(table[i]);
		}
		return entries;
	}

	// Restituisce true se la posizione è vuota o contiene sentinella
	private boolean isDisp(int i) {
		return (table[i] == null || table[i] == DEFUNCT);
	}

	// Restituisce l'indice della chiave o -(a+1)/-a se in a si può inserire k
	private int findSlot(int h, String k) {
		int a = -1;									//nessuna posizione disponibile
		int i = h;

		for (int p = 0; p != getCapacity(); p++) {	//si ferma se torna all'inizio
			if (isDisp(i)) {						//vuota o con sentinella
				if (a == -1) 						//prima posizione disponibile
					a = i;
				if (table[i] == null) 				//cella vuota
					break;
			}
			else if (table[i].getKey().equals(k))	//ricerca ha avuto successo
				return i;
			i = (i + p*p) % getCapacity();			//continua a cercare (ciclicamente)
		}
		return -a;                                  //oppure -(a+1)
	}
}
