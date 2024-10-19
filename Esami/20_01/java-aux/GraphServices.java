import java.util.*;

public class GraphServices {


	public static <V> void bfs(Graph<V> g, Node<V> source) {
		for (Node<V> nodo: g.getNodes()) {
			if (nodo == source)
				nodo.timestamp = 0;
			else
				nodo.timestamp = 10000;
			nodo.stato = Node.Stato.UNEXPLORED;
		}
		LinkedList<Node<V>> lv = new LinkedList<>();
		lv.add(source);
		source.stato = Node.Stato.EXPLORED;
		
		int i = 0;
		while (!lv.isEmpty()) {
			LinkedList<Node<V>> next = new LinkedList<>();
			
			for (Node<V> nodo: lv) {
				
				nodo.timestamp = i;	
				System.out.println("Livello " + nodo + ": " + nodo.timestamp);			
				for (Edge<V> lato: g.getOutEdges(nodo)) {
					Node<V> opposto = lato.getTarget();
					if (opposto.stato == Node.Stato.UNEXPLORED) {
						next.add(opposto);
						opposto.stato = Node.Stato.EXPLORED;
					}
				}
			}
			lv = next;
			i++;
		}	
		
		
	}

	public static <V> String sssp(Graph<V> g, Node<V> source) {
		MinHeap<Node<V>> pq = new MinHeap<>();
		HashMap<Node<V>, HeapEntry<Node<V>>> pqtokens = new HashMap<>();
		
		for (Node<V> nodo: g.getNodes()) {
			if (nodo == source)
				nodo.timestamp = 0;
			else
				nodo.timestamp = 100000;
			HeapEntry entry = pq.insert(nodo.timestamp, nodo);
			pqtokens.put(nodo, entry);
		}
		
		String ris = "Distanze dal nodo " + source + "[";
		while (!pq.isEmpty()) {
			Node<V> corrente = pq.removeMin().getValue();
			ris += corrente + ":" + corrente.timestamp + " ";
			pqtokens.remove(corrente);
			
			for (Edge<V> lato: g.getOutEdges(corrente)) {
				Node<V> opposto = lato.getTarget();
				HeapEntry<Node<V>> entry = pqtokens.get(opposto);
				
				if (entry != null) {
					int peso = lato.getWeight();
					if (peso + corrente.timestamp < opposto.timestamp) {
						opposto.timestamp = peso + corrente.timestamp;
						pq.replaceKey(entry, opposto.timestamp);
					}
				}
			}
		}			
		ris += "]";
		return ris;
	}

	public static <V> void apsp(Graph<V> g) {
		for (Node<V> nodo: g.getNodes()) {
			String dist = sssp(g, nodo);
			System.out.println(dist);			
		}
	}
}
