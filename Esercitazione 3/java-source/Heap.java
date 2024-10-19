public class Heap {

    public enum HEAP_TYPE {MAX_HEAP, MIN_HEAP};
    public HEAP_TYPE tipo;
    public int massimo;
    public HeapEntry[] array;
    public int contatore;

    public static class HeapEntry {
        //chiave, valore, posizione nell'array
        public int chiave;
        //int valore;
        public int pos;

        public HeapEntry(int c) {
            chiave = c;
            //valore = v;
        }
    }

    public Heap(HEAP_TYPE type, int capacity) {
        //quanto dell'array è utilizzato -> contatore
        //incrementa add   decrementa remove
        tipo = type;
        massimo = capacity;
        array = new HeapEntry[massimo];
        contatore = 0;
    }

    public HEAP_TYPE getType() {
        //per fare if minHeap else maxHeap
        return this.tipo;
    }

    public int peek() {
        return this.array[0].chiave;
    }

    public HeapEntry add(int key) {
        //creare oggetto, mettere la chiave nell'oggetto
        //mettere oggeto nel punto giusto, campo posizione
        //restituire riferimento oggetto
		if (contatore == massimo) {
			massimo *= 2;
			HeapEntry[] nuovo = new HeapEntry[massimo];
			for (int i = 0; i < contatore; i++) 
				nuovo[i] = array[i];
			this.array = nuovo;
		}
		
        HeapEntry nuovo = new HeapEntry(key);
        nuovo.pos = contatore;
        array[contatore] = nuovo;
        upheap(contatore);
        contatore++;
        return nuovo;
    }

    public int getEntryKey(HeapEntry e) {
        return e.chiave;
    }

    public int size() {
        return this.contatore;
    }

    public int poll() {                                
		//remove min o remove max
        int radice = peek();
        swap(0, size()-1);
        contatore--;
        downheap(0);
        return radice;
    }

    public static Heap array2heap(int[] array, HEAP_TYPE type) {
        //bottom-up O(n)
        //n down heap (no foglie)
        //si parte dall'ultimo elemento -> genitore, verifica proprietà heap,
        //downheap da qui fino alla radice (for) heapyfy reverse order
        int index = ((array.length-1)-1)/2;
        Heap ris = new Heap(type, 10);
        
        for (int i = 0; i < array.length; i++) {
			HeapEntry nuovo = new HeapEntry(array[i]);
			nuovo.pos = i;
			ris.array[i] = nuovo;
			ris.contatore++;
		}
		for (int i = index; i >= 0; i--) {
			ris.downheap(i);
		}
        return ris;
    }

    public void print() {
        for (int i = 0; i < contatore; i++) 
            System.out.print(array[i].chiave + " ");
        System.out.println();
    }

    public static void heapSort(int[] array) {
        //ordine in senso crescente sfruttando l'heap heap minHeap
        Heap h = Heap.array2heap(array, HEAP_TYPE.MIN_HEAP);
        for (int i = 0; i < array.length; i++) 
			array[i] = h.poll();
    }

    public void updateEntryKey(HeapEntry e, int key) {
		e.chiave = key;
		upheap(e.pos); 
		downheap(e.pos);
    }

    public void swap(int i, int j) {
        HeapEntry temp = array[i];
        array[i] = array[j];
        array[i].pos = i;
        array[j] = temp;
        array[j].pos = j;
    }

    public void upheap(int j) {
		if (this.tipo == HEAP_TYPE.MIN_HEAP) {
			while (j > 0) {
				int p = (j - 1)/2;
				if (array[j].chiave < array[p].chiave) {
					swap(j, p);
					j = p;
				}
				else break;
			}
		}
		else if (this.tipo == HEAP_TYPE.MAX_HEAP) {
			while (j > 0) {
				int p = (j - 1)/2;
				if (array[j].chiave > array[p].chiave) {
					swap(j, p);
					j = p;
				}
				else break;
			}
		}
    }

    public void downheap(int j) {
		if (this.tipo == HEAP_TYPE.MIN_HEAP) {
			while ((2*j + 1) < this.size()) {
				int sx = 2*j + 1;
				int small = sx;
				if (2*j + 2 < this.size()) {
					int dx = 2*j + 2;
					if (array[sx].chiave > array[dx].chiave)
						small = dx;
				}
				if (array[small].chiave < array[j].chiave) {
					swap(j, small);
					j = small;
				}
				else break;
			}
		}
		else if (this.tipo == HEAP_TYPE.MAX_HEAP) {
			while ((2*j + 1) < this.size()) {
				int sx = 2*j + 1;
				int big = sx;
				if (2*j + 2 < this.size()) {
					int dx = 2*j + 2;
					if (array[sx].chiave < array[dx].chiave)
						big = dx;
				}
				if (array[big].chiave > array[j].chiave) {
					swap(j, big);
					j = big;
				}
				else break;
			}
		}
    }
}
