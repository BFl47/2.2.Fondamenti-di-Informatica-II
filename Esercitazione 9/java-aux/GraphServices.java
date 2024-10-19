import java.util.*;

public class GraphServices {
	
	public static <V> void bfs(Graph<V> g) {
		
		for (Node<V> nodo: g.getNodes()) 
			nodo.stato = Node.Stato.UNEXPLORED;			

		for (Node<V> nodo: g.getNodes()) {
			if (nodo.stato == Node.Stato.UNEXPLORED) 
				bfs(g, nodo);
		}
	}
	
	public static <V> void bfs(Graph<V> g, Node<V> n) {
		LinkedList<Node<V>> livello = new LinkedList<Node<V>>();
		
		livello.add(n);
		n.stato = Node.Stato.EXPLORED;
		
		while (!livello.isEmpty()) {
			LinkedList<Node<V>> next_livello = new LinkedList<Node<V>>();
			
			for (Node corrente: livello) {
				System.out.println(corrente);
				for (Object o: g.getOutEdges(corrente)) {
					Edge arco = (Edge) o;
					Node opposto = arco.getTarget();
					if (opposto.stato == Node.Stato.UNEXPLORED) {
						opposto.stato = Node.Stato.EXPLORED;
						next_livello.add(opposto);
					}
				}
			}
			livello = next_livello;
		}			
	}
	
	public static <V> int getNumIn(Graph<V> g, Node<V> target){
		int i = 0;
		
    	for (Node nodo: g.getNodes()) {
			for (Object o: g.getOutEdges(nodo)) {
				Edge arco = (Edge) o;
				if (arco.getTarget() == target)
					i++;
			}
		}
		return i;
    }
	
	public static <V> void sssp(Graph<V> g, Node<V> source) {
		
		HashMap<Node<V>, Integer> d = new HashMap<>();					//nodo, distanza corrente
		LinkedHashMap<Node<V>, Integer> cloud = new LinkedHashMap<>();	//nodo, distanza immutabile
		
		MinHeap<V> pq = new MinHeap<V>();							//distanza, nodo.getElem()
		HashMap<Node<V>, HeapEntry<V>> pq_pos = new HashMap<>();	//nodo, entry in pq
		
		for (Node<V> nodo: g.getNodes()) {
			if (nodo == source) 
				d.put(nodo, 0);
			else
				d.put(nodo, 666);
			HeapEntry entry = pq.insert(d.get(nodo), nodo.getElement());
			pq_pos.put(nodo, entry);
		}
		
		while (!pq.isEmpty()) {
			HeapEntry<V> entry = pq.removeMin();
			int chiave = entry.getKey();
			V val = entry.getValue();
			
			Node corrente = new Node(val);
			
			for (Node nodo: g.getNodes()) {
				if (nodo.getElement() == val)
					corrente = nodo;
			}
			
			cloud.put(corrente, chiave);
			pq_pos.remove(corrente);
			
			for (Object o: g.getOutEdges(corrente)) {
				Edge arco = (Edge) o;
				Node opposto = arco.getTarget();
				int peso = arco.getWeight();
				if (cloud.get(opposto) == null) {
					int dist = d.get(corrente) + peso;
					if (dist < d.get(opposto)) {
						d.put(opposto, dist);
						pq.replaceKey(pq_pos.get(opposto), dist);
					}
				}
			}
		}
		for (Node<V> nodo: cloud.keySet()) 
			System.out.println(nodo.getElement() + " " + cloud.get(nodo));			
	}
	
	public static <V> void mst(Graph<V> G) {
		int n = G.getNodes().size();
		
		Partition cluster = new Partition(G.getNodes());

		TreeMap<Integer, LinkedList<Edge<V>>> pq = new TreeMap<>();
		
		for (Edge<V> arco: G.getEdges()) {
			
			if (pq.get(arco.getWeight()) == null) {
				LinkedList<Edge<V>> lista = new LinkedList<Edge<V>>();
				pq.put(arco.getWeight(), lista);
			}
			LinkedList<Edge<V>> lista = pq.get(arco.getWeight());
			lista.add(arco);
			pq.put(arco.getWeight(), lista);
		}
		LinkedList<Edge<V>> tree = new LinkedList<Edge<V>>();
		
		while (tree.size() < n-1 && !pq.isEmpty()){
			
			Map.Entry<Integer, LinkedList<Edge<V>>> entry = pq.firstEntry();
			LinkedList<Edge<V>> archi = entry.getValue();
			
			if (archi.size() == 0) {
				pq.remove(entry.getKey());
				continue;
			}
			Edge<V> arco = archi.pollFirst();
			Node u = arco.getSource();
			Node v = arco.getTarget();
			
			if (cluster.find(u.map) != cluster.find(v.map)) {
				tree.add(arco);
				cluster.union(u.map, v.map);
			}
		}
		for (Edge arco: tree) 
			System.out.println(arco.getSource() + " " + arco.getTarget());
	}
}
