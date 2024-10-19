public class Kth1 {
	public Heap min;
	public Heap max;
	int k;
	
    public Kth1(int k) {
		min = new Heap(Heap.HEAP_TYPE.MIN_HEAP, 10);
		max = new Heap(Heap.HEAP_TYPE.MAX_HEAP, 10);
		this.k = k;
	}

    public void insert(int key) {
		if (max.contatore < k) {
			max.add(key);
		}
		else if (key > get()) {
			min.add(key);
		}
		else if (key < get()) {
			int chiave = max.poll();
			max.add(key);
			min.add(chiave);
		}		
        return;
    }

    public int get() {
		if (max.contatore >= k)
			return max.peek();
		return -1;
    }

    public void remove() {
		max.poll();
		if (max.contatore < k && min.contatore > 0) {
			int chiave = min.poll();
			max.add(chiave);
		}
        return;
    }

}
