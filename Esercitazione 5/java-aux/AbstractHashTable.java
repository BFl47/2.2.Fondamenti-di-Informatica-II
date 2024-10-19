import java.util.Random;
import java.util.LinkedList;

public abstract class AbstractHashTable {
	private int capacity; // dim. tabella
	private int n = 0; // numero di entry nella tabella
	private int prime; // numero primo
	private long a, b; // coefficienti per MAD
	private double maxLambda; // fattore di carico massimo

	// La classe Entry --> coppie (chiave, valore)
	class Entry {
		private String key;
		private int value;
		public Entry(String k, int v) {
			key = k;
			value = v;
		}
		public String getKey() { // Restituisce chiave
			return key;
		}
		public int getValue() { // Restituisce valore
			return value;
		}
		public void setValue(int v) { // Aggiorna valore
			value = v;
			return;
		}
		public String toString() {
			return "(" + getKey() + ", " + Integer.toString(getValue()) + ")";
		}
	}

	// Costruttori di AbstractHashTable
	public AbstractHashTable(int cap, int p, double lambda) {
		capacity = cap;
		prime = p;
		maxLambda = lambda;
		Random gen = new Random();
		a = gen.nextInt(prime) + 1;
		b = gen.nextInt(prime);
		createTable();
	}
	public AbstractHashTable(int cap, int p) {
		this(cap, p, 0.5); // massimo fattore di carico predefinito
	}
	public AbstractHashTable(int cap) {
		this(cap, 109345121); // primo predefinito
	}
	public AbstractHashTable() {
		this(5); // capacità predefinita
	}

	// Metodi ausiliari comuni a tutte le classi

	// metodo che implementa la funzione hash (hash code + compressione)
	// Si ricordi che ogni oggetto Java implementa hashcode,
	// a cominciare dalle stringhe
	protected int hashFunction(String k) {
		int h = k.hashCode();
		int max = prime - 1;
		int min = 1;

		int h1 = (int) ((a*h + b) % prime) % this.getCapacity();
		return 	Math.abs(h1);
	}

	// metodo che aggiorna la dimensione della tabella hash	(N)
	protected void resize(int newCap) {

		LinkedList<Entry> entries = (LinkedList<Entry>) entrySet();
		capacity = newCap;
		n = 0;
		createTable();

		for (Entry e: entries)
			this.put(e.getKey(), e.getValue());
	}

	// Metodi pubblici comuni a tutte le classi

	// restituisce true se la tabella è vuota
	public boolean isEmpty() { // restituisce true se tabella vuota
		return n == 0;
	}

	// restituisce il numero di chiavi presenti nella tabella
	public int size() {
		return n;
	}

	// restituisce la capacità della tabella
	public int getCapacity() {
		return this.capacity;
	}

	// incrementa il numero n di chiavi presenti
	public void incSize() {
		n++;
	}

	// decrementa il numero n di chiavi presenti
	public void decSize() {
		n--;
	}

	// restituisce valore max. per il fattore di carico (si effettua resize se superato)
	public double getMaxLambda() {
		return 0.5;
	}

	// Stampa una rappresentazione delle coppire presenti secondo
    // il formato [(k1, v1),(k2,v2), ... ,(kn, vn)]
	public void print() {
		System.out.println("capacity: " + getCapacity());
		LinkedList<Entry> l = (LinkedList<Entry>)this.entrySet();
		System.out.print("[ ");
		for (Entry e: l)
			System.out.print(e + " ");
		System.out.println("]");
	}

    protected boolean isPrimo(int n) {
        if (n % 2 == 0) return false;
        for (int i = 3; i < Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

	// Metodi astratti da implementare nelle sottoclassi
	// Descrizioni più dettagliate nelle classi ChainHashTable e OpenHashTable
	protected abstract void createTable(); // inizializza la tabella hash
	public abstract int get(String k); // restituisce il valore associato alla chiave k
	public abstract int put(String k, int value); // inserisce un coppia
	public abstract int remove(String k); // rimuove la coppia con chiave k
	public abstract Iterable<Entry> entrySet(); // restituisce un Iterable contentente tutte le coppie presenti

}
