public class Heap {
	public int[] array;
	int contatore;
	
	public Heap(int capacity) {
		array = new int[capacity];
		contatore = 0;
	}
	public int min() {
		return array[0];
	}
	public int size() {
		return contatore;
	}
	public int insert(int nuovo) {
		array[contatore] = nuovo;
		upheap(contatore);
		contatore++;
		return nuovo;
	}
	public int removeMin() {
		int radice = min();
		swap(0, size()-1);
		contatore--;
		downheap(0);
		return radice;
	}
	protected void swap (int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	protected void upheap(int i) {
		while (i > 0) {
			int p = (i-1)/2;
			if (array[i] < array[p]) {
				swap(i, p);
				i = p;
			}
			else break;
		}
	}
	protected void downheap(int i) {
		while ((2*i + 1) < size()) {
			int sx = 2*i + 1;
			int small = sx;
			if (2*i + 2 < size()) {
				int dx = 2*i + 2;
				if (array[sx] > array[dx])
					small = dx;
			}
			if (array[small] < array[i]) {
				swap(i, small);
				i = small;
			}
			else break;
		}
	}
	public static Heap array2heap(int[] arr) {
		int id = ((arr.length-1)-1)/2;
		Heap ris = new Heap(arr.length);
		
		for (int i = 0; i < arr.length; i++) {
			ris.array[i] = arr[i];
			ris.contatore++;
		}
		for (int i = id; i >= 0; i--) 
			ris.downheap(i);
		return ris;
	}
	public void print() {
		for (int i = 0; i < contatore; i++) 
			System.out.print(array[i] + " ");
		System.out.println();
	}
}
