import java.util.*;

public class BST<V> {
	
	private class Node<V> {
        private int key;
        private V value;
        private Node<V> left;
        private Node<V> right;

        public Node(int key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
			return "(" +  this.key + ", " + this.value + ")";
		}
		public void setNull() {
			this.key = 0;
			this.value = null;
		}
    }
    
	public Node root; 
    
    public BST(int key, V value) {
		this.root = new Node(key, value);
    }

    public void insert(int k, V v) {
		insert_aux(k, v, this.root);
    }
    
    public void insert_aux(int k, V v, Node n) {
		if (k == n.key) 
			n.value = v;
		
		else if (k < n.key && n.left == null) 
			n.left = new Node(k, v);
		
		else if (k > n.key && n.right == null) 
			n.right = new Node(k, v);
			
		else if (k < n.key) 
			insert_aux(k, v, n.left);
			
		else if (k > n.key)
			insert_aux(k, v, n.right);
	}
		
    public V find(int k) {
		return find_aux(k, this.root);        
    }
    
    public V find_aux(int k, Node n) {
		if (n == null)
			return null;
		if (k == n.key) 
			return (V) n.value;
		else if (k < n.key)
			return find_aux(k, n.left);
		else if (k > n.key)
			return find_aux(k, n.right);
		else
			return null;
	}

    public int findMin() {
		Node min = findMin_aux(this.root);
		return min.key;
    }
    
    public Node findMin_aux(Node n) {
		if (n.left == null) 
			return n;
		else
			return findMin_aux(n.left);
	}

    public void removeMin() {
        int min = findMin();
        remove(min);
    }

    public void remove(int k) {
		remove_aux(k, this.root);
    }
    
    public void remove_aux(int k, Node n) {
		if (k == n.key && isEsterno(n)) {
			n.setNull();
			riarrange(this.root);
		}
		
		else if (k == n.key && n.left == null && n.right != null) {
			Node daElim = n.right;
			n.key = n.right.key;
			n.value = n.right.value;
			daElim.setNull();
			riarrange(this.root);
		}
			
		else if (k == n.key && n.left != null && n.right == null) {
			Node daElim = n.left;
			n.key = n.left.key;
			n.value = n.left.value;
			daElim.setNull();
			riarrange(this.root);
		}
		
		else if (k == n.key && isInterno(n)) {
			Node sost = findMin_aux(n.right);
			n.key = sost.key;
			n.value = sost.value;
			sost.setNull();
			riarrange(this.root);
		}
		else if (k < n.key)
			remove_aux(k, n.left);
		else if (k > n.key)
			remove_aux(k, n.right);
	}
	
	public void riarrange(Node n) {
		if (n == null)
			return;
			
		if (!isEsterno(n) && n.left != null && n.left.key == 0) {
			if (isEsterno(n.left)) {
				n.left = null;
				riarrange(n);
			}
			else {
				Node corrente = n.left;
				Node min;
				if (corrente.left != null) 
					min = findMin_aux(corrente.left);
				else 
					min = findMin_aux(corrente.right);
					
				n.left.key = min.key;
				n.left.value = min.value;
				min.setNull();
				riarrange(n.left);
			}
		}
		
		else if (!isEsterno(n) && n.right != null && n.right.key == 0) {
			if (isEsterno(n.right)) {
				n.right = null;
			}
			else {
				Node corrente = n.right;
				Node min;
				if (corrente.left != null) 
					min = findMin_aux(corrente.left);
				else 
					min = findMin_aux(corrente.right);
					
				n.right.key = min.key;
				n.right.value = min.value;
				min.setNull();
				riarrange(n.right);
			}	
		}
		riarrange(n.left);
		riarrange(n.right);
	}
	
	public boolean isEsterno(Node n) {
		if (n.left == null && n.right == null)
			return true;
		return false;
	}
	
	public boolean isInterno(Node n) {
		if (n.left != null && n.right != null)
			return true;
		return false;
	}
	
	private void print(Node<V> t, int level) {
        if (t == null)
            return;
        for (int i = 0; i < level - 1; i++) {
            System.out.print("   ");
        }
        if (level > 0) {
            System.out.print(" |--");
        }
        System.out.println(t.key);

        print(t.left, level + 1);
        print(t.right, level + 1);
    }
	
	void print(){
        print(this.root, 0);
    }
    
    int predecessor(int k) {
		return predecessor_aux(k, root);
	}
	
	int predecessor_aux(int k, Node n) {
		if (n == null)
			return -1;
		if (k <= n.key) 
			return predecessor_aux(k, n.left);
		else {
			int min = predecessor_aux(k, n.right);
			if (min == -1)
				return n.key;
			else 
				return min;
		}
	}
	LinkedList ordina(BST t) {
		
		
	}
}
