import java.util.*;

public class Sort {

    /* Disponibile in libreria java */
    public void quickSortDefault(int[] array) {
        Arrays.sort(array);
    }

    public void mergeSort(int[] array) {
        int n = array.length;
        if (n < 2)
            return;

        int mid = n / 2;
        int[] a = Arrays.copyOfRange(array, 0, mid);
        int[] b = Arrays.copyOfRange(array, mid, n);

        mergeSort(a);
        mergeSort(b);
        merge(a, b, array);
    }

    public void merge(int[] a, int[] b, int[] array) {
        int i = 0, j = 0;
        while (i+j < array.length) {
            if (j == b.length || (i < a.length && a[i] < b[j])) {
                array[i+j] = a[i];
                i++;
            } else {
                array[i+j] = b[j];
                j++;
            }
        }
    }

    public void heapSort(int[] array) {
        Heap h = Heap.array2heap(array);
        for (int i = 0; i < array.length; i++) {
			array[i] = h.removeMin();
		}
    }

    public void insertionSort(int[] array) {
		int div = 0;
		int i = div+1;
		while (div < array.length-1) {
			if (array[div] > array[i]) {
				int temp = array[div];
				array[div] = array[i];
				array[i] = temp;
			}
			div++;
			i++;
			for (int j = div; j > 0; j--) {
				if (array[j] < array[j-1]) {
					int temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
        }
    }
		
    public void selectionSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int idx = idx_minimo(array, i);
            int temp = array[idx];
            array[idx] = array[i];
            array[i] = temp;
        }
        return;
    }

    public int idx_minimo(int[] array, int i) {
        int minimo = 666;
        int ris = 0;
        for (int j = i; j < array.length; j++) {
            if (array[j] < minimo) {
                minimo = array[j];
                ris = j;
            }
        }
        return ris;
    }

    public void quickSort(int[] array) {
        quickSortInPlace(array, 0, array.length-1);
    }
    public void quickSortInPlace(int[] array, int a, int b) {
		if (a >= b) return;
		int left = a;
		int right = b-1;
		int pivot = array[b];
		int temp;
		while (left <= right) {
			while (left <= right && array[left] < pivot)
				left++;
			while (left <= right && array[right] > pivot)
				right++;
			if (left <= right) {
				temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				left++; right--;
			}
		}
		temp = array[left];
		array[left] = array[b];
		array[b] = temp;
		
		quickSortInPlace(array, a, left-1);
		quickSortInPlace(array, left+1, b);		
	}

    public void radixSort(int[] array) {
		
		int[] t0 = new int[array.length];
		int[] t1 = new int[array.length];
		
		for (int b = 0; b < 4*8; b++) 
			bucketSort2(array, array.length, b, t0, t1);
    }

    //ordina l'array rispetto bit-esimo bit
	public void bucketSort2(int[]a, int n, int bit, int[] t0, int[] t1) {
		int i0 = 0, i1 = 0;
		int mask = 0x1 << bit; //left-shift di bit posizioni
		for (int i= 0; i < n; i++) {
			if ((a[i] & mask) != 0) {	//estrae bit 1
				t1[i1] = a[i];
				i1++;
			}
			else {						//estrae bit 0
				t0[i0] = a[i];
				i0++;
			}
		}
		for (int i = 0; i < i0; i++) 
			a[i] = t0[i];
		i1 = 0;
		for (int i = i0; i < n; i++) {
			a[i] = t1[i1];
			i1++;
		}
		
	}
    
    public void bucketSort(int[] array) {
        int massimo = massimo(array);
        int[] B = new int[massimo+1];
        for (int i = 0; i < array.length; i++)
            B[array[i]]++;
        int j = 0;
        for (int i = 0; i < B.length; i++) {
            while (B[i] != 0) {
                array[j] = i;
                B[i]--;
                j++;
            }
        }
        return;
    }

    public int massimo(int[] array) {
        int massimo = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > massimo)
                massimo = array[i];
        }
        return massimo;
    }
}
