import java.util.*;

public class GraphServices {

    public static <V> void bfs(Graph<V> g) {
        for (Node<V> n: g.getNodes()) {
			if (n.state == Node.Status.UNEXPLORED) {
				System.out.print(n.getValue() + " ");
				bfsDaNodo(n, g);
				System.out.println();
			}
		}
    }
	
	public static <V> void bfsDaNodo(Node<V> n, Graph<V> g) {
		LinkedList<Node<V>> level = new LinkedList<Node<V>>();
		level.add(n);
		n.state = Node.Status.EXPLORED;
		
		while (!level.isEmpty()) {
			LinkedList<Node<V>> nextLevel = new LinkedList<Node<V>>();
			for (Node<V> node: level) {
				for (Edge<V> e: g.getOutEdges(node)) {
					Node<V> opposite = e.getTarget();
					
					if (opposite.state == Node.Status.UNEXPLORED) {
						nextLevel.add(opposite);
						opposite.state = Node.Status.EXPLORED;
						System.out.print(opposite.getValue() + " ");
					}
				}
			}
			level = nextLevel;
		}
	}
	
    public static <V> void sssp(Graph<V> g, Node<V> source) {
		MinHeap<Node<V>> pq = new MinHeap<Node<V>>();
		HashMap<Node<V>, HeapEntry<Node<V>>> pqTokens = new HashMap<>();
		
		for (Node<V> n: g.getNodes()) {
			if (n == source)
				n.dist = 0;
			else
				n.dist = 10000;
			HeapEntry<Node<V>> entry = pq.insert(n.dist, n);
			pqTokens.put(n, entry);
		}
		
		while(!pq.isEmpty()) {
			Node<V> nodo = pq.removeMin().getValue();
			
			System.out.println(nodo.getValue() + " " + nodo.dist);
			pqTokens.remove(nodo);
			
			for (Edge<V> lato: g.getOutEdges(nodo)) {
				Node<V> opposto = lato.getTarget();
				HeapEntry<Node<V>> entryopp = pqTokens.get(opposto);
				
				if (entryopp != null) {
					int peso = lato.getWeight();
	
					if (peso + nodo.dist < opposto.dist) {
						pq.replaceKey(entryopp, peso + nodo.dist);
						opposto.dist = peso + nodo.dist;
					}
				}
			}
		}
    }
    
    public static <V> void mst(Graph<V> g) {
		Partition<V> P;
		MinHeap<Edge<V>> pq = new MinHeap<Edge<V>>();
		
		for (Edge<V> lato: g.getEdges()) 
			pq.insert(lato.getWeight(), lato);
		int i = 0;
		for (Node<V> nodo: g.getNodes()) 
			nodo.map = i++;
			
		P = new Partition(g.getNodes());
		
		while (!pq.isEmpty()) {
			Edge<V> lato = pq.removeMin().getValue();
			Node<V> a = lato.getSource();
			Node<V> b = lato.getTarget();
			
			if (P.find(a.map) != P.find(b.map)) {
				System.out.println(a + " " + b);
				P.union(a.map, b.map);
			}
				
		}
		
    }
}
