
public class MatriceSparsa {

	private class Elem{
        int i;
        int j;
        int x;
        Elem next;

        public Elem(int i, int j, int x) {
            this.i = i;
            this.j = j;
            this.x = x;
            this.next = null;
        }
	}
	
    private int m;
    private int n;
    private Elem head;

	public MatriceSparsa(int m, int n) {
        this.m = m;
        this.n = n;
	}

	public int getNumRow() {
		return this.m;
	}

	public int getNumCol() {
		return this.n;
	}

	public void set(int i, int j, int x) {
		if (i >= this.m || j >= this.n) {
			System.out.println("indici fuori range");
			return;
		}
		if (x == 0)
			return;
		Elem nuovo = new Elem(i, j, x);
		Elem testa = this.head;
		
        if (testa == null || testa.i > i || (testa.i == i && testa.j >= j)) {		// IN TESTA - Aggiunge
			nuovo.next = testa;
            this.head = nuovo;
			return;
		}
		if (testa.i == i && testa.j == j) {											// IN TESTA - Modifica se già presente
			testa.x = x;
			return;
		}
		while (testa.next != null) {
			if (testa.i == i && testa.j == j) {										// IN MEZZO - Modifica se già presente
				testa.x = x;
				return;
			}
			if (testa.next.i > i || (testa.next.i == i && testa.next.j > j)) {		// IN MEZZO - Aggiunge
				nuovo.next = testa.next;
				testa.next = nuovo;
			}
			testa = testa.next;
		}
		if (testa.i == i && testa.j == j) {											// IN CODA - Modifica se già presente
			testa.x = x;
			return;
		}		
		testa.next = nuovo;															// IN CODA - Aggiunge
	}	

	public int get(int i, int j) {
		Elem corrente = head;
        while (corrente != null) {
            if (corrente.i == i && corrente.j == j)
                return corrente.x;
            corrente = corrente.next;
        }
		return 0;
	}

	public String toString() {
		if (this == null)
			return "matrice vuota\n";
		String ris = "";
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
				if (get(i, j) > 9)
					ris += get(i, j) + " ";
				else 
					ris += get(i, j) + "  ";
			}
            ris += "\n";
        }
        return ris;
	}

	public static MatriceSparsa add(MatriceSparsa mat1, MatriceSparsa mat2) {
		if (mat1.getNumRow() != mat2.getNumRow() || mat1.getNumCol() != mat2.getNumCol())
			return null;
		MatriceSparsa ris = new MatriceSparsa(mat1.getNumRow(), mat1.getNumCol());
		for (int i = 0; i < mat1.getNumRow(); i++) {
			for (int j = 0; j < mat1.getNumRow(); j++) {
				int n = mat1.get(i, j) + mat2.get(i, j);
				if (n != 0) 
					ris.set(i, j, n);
			}
		}
		return ris;				
	}

	public static MatriceSparsa tra(MatriceSparsa mat1) {
		MatriceSparsa ris = new MatriceSparsa(mat1.getNumCol(), mat1.getNumRow());
		for (int i = 0; i < ris.getNumRow(); i++) {
			for (int j = 0; j < ris.getNumCol(); j++) {
				int n = mat1.get(j, i);
				if (n != 0)
					ris.set(i, j, n);
			}
		}
		return ris;
	}

	public static MatriceSparsa mul(MatriceSparsa mat1, MatriceSparsa mat2) {
		if (mat1.getNumCol() != mat2.getNumRow()) {
			System.out.println("indici not ok");
			return null;
		}
		MatriceSparsa ris = new MatriceSparsa(mat1.getNumRow(), mat2.getNumCol());
		// i x k     k x j
		for (int i = 0; i < mat1.getNumRow(); i++) {
			for (int j = 0; j < mat1.getNumCol(); j++) {
				int somma = 0;
				for (int k = 0; k < mat1.getNumCol(); k++) {
					somma += mat1.get(i, k) * mat2.get(k, j);
				}
				if (somma != 0)
					ris.set(i, j, somma);
			}
		}
		return ris;
	}
}
