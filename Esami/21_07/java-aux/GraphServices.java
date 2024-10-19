import java.util.*;

public class GraphServices {

	public static <V> void bfs(Graph<V> g, Graph.GraphNode<V> source) {
	
		g.resetStatus();
		
		LinkedList<Graph.GraphNode<V>> lv = new LinkedList<>();
		lv.add(source);
		source.state = Graph.GraphNode.Status.EXPLORED;
		
		int i = 0;
		while (!lv.isEmpty()) {
			LinkedList<Graph.GraphNode<V>> next = new LinkedList<>();
			
			for (Graph.GraphNode<V> nodo: lv) {
				nodo.timestamp = i;
				System.out.println("Livello " + nodo.value + ": " + nodo.timestamp);
				
				
				for (Graph.GraphNode<V> opposto: g.getOutNeighbors(nodo)) {
					if (opposto.state == Graph.GraphNode.Status.UNEXPLORED) {
						next.add(opposto);
						opposto.state = Graph.GraphNode.Status.EXPLORED;
					}
				}
				
			}
			i++;
			lv = next;
		}
	}




























	//public static <V> void bfs(Graph<V> g, Graph.GraphNode<V> source) {
		//g.resetStatus();
		//for (Graph.GraphNode<V> nodo: g.getNodes()) {
			//if (nodo == source)
				//nodo.timestamp = 0;
			//else
				//nodo.timestamp = 10000;
		//}
		
		//LinkedList<Graph.GraphNode<V>> livello = new LinkedList<Graph.GraphNode<V>>();
		//livello.add(source);
		//source.state = Graph.GraphNode.Status.EXPLORED;
		//int i = 0;
		//while (!livello.isEmpty()) {
			//LinkedList<Graph.GraphNode<V>> next = new LinkedList<Graph.GraphNode<V>>();
			
			//for (Graph.GraphNode<V> corrente: livello) {
				//corrente.timestamp = i;
				
				//for (Graph.GraphNode<V> opposto: g.getOutNeighbors(corrente)) {
					//if (opposto.state == Graph.GraphNode.Status.UNEXPLORED) {
						//opposto.state = Graph.GraphNode.Status.EXPLORED;
						//next.add(opposto);
					//}
				//}
			//}
			
			//livello = next;
			//i++;			
		//}
		
		//for (Graph.GraphNode<V> nodo: g.getNodes()) {
			//if (nodo.timestamp != 10000)
				//System.out.println("Livello " + nodo.value + ": " + nodo.timestamp);
		//}
			
	//}

}
