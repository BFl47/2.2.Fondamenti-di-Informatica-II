import java.util.LinkedList;
import java.util.ArrayList;

public class ChainHashTable extends AbstractHashTable {
	// Un array di LinkedList per le liste di trabocco
	// L'array table implementa la tabella hash
	private LinkedList<Entry> [] table;

	// Costruttori
	public ChainHashTable(int cap, int p, double lambda) {
		super(cap, p, lambda);
	}
	public ChainHashTable(int cap, int p) {
		super(cap, p); // massimo fattore di carico predefinito
	}
	public ChainHashTable(int cap) {
		super(cap); // primo predefinito
	}
	public ChainHashTable() {
		super(); // capacità predefinita
	}

	// Metodi non implementati in AbstractHashTable

	// Inizializza una tabella hash vuota secondo i parametri passati al costruttore
	protected void createTable() {
		table = new LinkedList[this.getCapacity()];
	}

	// Restituisce il valore (intero) associato alla chiave k
	// Restituisce -1 se la chiave è assente
	public int get(String k) {
		int idx = hashFunction(k);
		if (table[idx] != null) {
			for (Entry e: table[idx]) {
				if (e.getKey() == k)
					return e.getValue();
			}
		}
		return -1;
	}

	// Aggiorna il valore associato alla chiave k
	// Restituisce il vecchio valore o -1 se la chiave non è presente
	public int put(String k, int value) {
		int idx = hashFunction(k);

		if (table[idx] == null)
			table[idx] = new LinkedList<Entry>();
		else {
			for (Entry e: table[idx]) {
				if (e.getKey() == k) {
					int vecchio = e.getValue();
					e.setValue(value);
					return vecchio;
				}
			}
		}
		table[idx].add(new Entry(k, value));
		this.incSize();

		double lambda = (double) table[idx].size() / (double) getCapacity();
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
		int idx = hashFunction(k);
		if (table[idx] != null) {

			for (Entry e: table[idx]) {
				if (e.getKey() == k) {
					int vecchio = e.getValue();
					table[idx].remove(e);
					this.decSize();
					return vecchio;
				}
			}
		}
		return -1;
	}

	// Restituisce un oggetto Iterable contenente tutte le coppie presenti
	// nella tabella hash
	public Iterable<Entry> entrySet() {
		LinkedList<Entry> ris = new LinkedList<Entry>();
		for (int i = 0; i < this.getCapacity(); i++) {
			if (table[i] != null) {
				for (Entry e: table[i])
					ris.add(e);
			}
		}
		return ris;
	}
}
