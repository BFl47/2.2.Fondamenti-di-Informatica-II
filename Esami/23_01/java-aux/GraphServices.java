import java.util.*;

public class GraphServices {
	public static <V> int max_dist(Graph<V> g, Graph.GraphNode<V> source) {
		g.resetStatus();
		
		LinkedList<Graph.GraphNode<V>> lv = new LinkedList<>();
		lv.add(source);
		source.state = Graph.GraphNode.Status.EXPLORED;
		
		int i = 0;
		while (!lv.isEmpty()) {
			LinkedList<Graph.GraphNode<V>> next = new LinkedList<>();
			
			for (Graph.GraphNode<V> nodo: lv) {
				nodo.timestamp = i;
				
				for (Graph.GraphNode<V> opposto: g.getOutNeighbors(nodo)) {
					if (opposto.state == Graph.GraphNode.Status.UNEXPLORED) {
						next.add(opposto);
						opposto.state = Graph.GraphNode.Status.EXPLORED;
					}
				}
			}
			
			lv = next;
			i++;
		}
		
		return i-1;
	
	}
		

	//public static <V> int max_dist(Graph<V> g, Graph.GraphNode<V> source) {
		//g.resetStatus();
		
		//int i = 0;
		//LinkedList<Graph.GraphNode<V>> livello = new LinkedList<Graph.GraphNode<V>>();
		//livello.add(source);
		//source.state = Graph.GraphNode.Status.EXPLORED;
		//int max = 0;
		
		//while (!livello.isEmpty()) {
	
			//LinkedList<Graph.GraphNode<V>> nextLivello = new LinkedList<Graph.GraphNode<V>>();
			
			//for (Graph.GraphNode<V> corrente: livello) {
				//corrente.timestamp = i;
				//if (i > max) 
					//max = i;
				//for (Graph.GraphNode<V> opposto: g.getOutNeighbors(corrente)) {
					//if (opposto.state == Graph.GraphNode.Status.UNEXPLORED) {
						//opposto.state = Graph.GraphNode.Status.EXPLORED;
						//nextLivello.add(opposto);
					//}
				//}
			//}
			
			//livello = nextLivello;
			//i++;
		//}
		
		//return max;
	//}

}
