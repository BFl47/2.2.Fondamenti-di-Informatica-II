
public class Labirinto {

	private static enum Cella { VUOTA, PIENA };

	private int n;
    private Cella[][] m;
    private boolean[][] marcata;

    public Labirinto(int n) {
        this.n = n;
        m = new Cella[n][n];
        marcata = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				marcata[i][j] = false;
				m[i][j] = Cella.VUOTA;
			}
		}
    }

	public void setPiena(int r, int c){
		m[r][c] = Cella.PIENA;
    }

	private boolean uscita(int r, int c){
  		if (r == n-1 && c == n-1)
            return true;
        return false;
    }

	public boolean percorribile(int r, int c){
		if (r < 0 || r >= n || c < 0 || c >= n || m[r][c] == Cella.PIENA || marcata[r][c])
			return false;		
		return true;
	}

	private boolean uscitaRaggiungibileDa(int r, int c){
		if (!percorribile(r, c))
			return false;
		marcata[r][c] = true;
		if (uscita(r, c))
			return true;
		if (uscitaRaggiungibileDa(r+1, c) || uscitaRaggiungibileDa(r-1, c) ||
			uscitaRaggiungibileDa(r, c+1) || uscitaRaggiungibileDa(r, c-1))
			return true;
		marcata[r][c] = false;
		return false;
	}

	public boolean risolvibile(){
		if (uscitaRaggiungibileDa(0, 0))
			return true;
		return false;
	}



	public String toString() {
		String ris = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (marcata[i][j] == true)
                    ris += '+';
                else if (m[i][j] == Cella.PIENA)
                    ris += '#';
                else
                    ris += '.';
            }
            ris += '\n';
        }
        return ris;

	}
}

/*
 
		5				5
		.....			....#
		###.#			###.#
		....#			....#
		#.##.			#.##.
		.....			...#.

*/
