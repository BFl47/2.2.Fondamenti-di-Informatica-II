import java.util.*;

public class GraphServices {
	public static <V> void kdist(Graph<V> g, Graph.GraphNode<V> source, int k) {
		g.resetStatus();
		for (Graph.GraphNode<V> nodo: g.getNodes()) {
			if (nodo == source)
				nodo.timestamp = 0;
			else
				nodo.timestamp = 100000;
		}
		
		int i = 1;
		
		LinkedList<Graph.GraphNode<V>> lv = new LinkedList<>();
		lv.add(source);
		source.state = Graph.GraphNode.Status.EXPLORED;
		
		while (!lv.isEmpty()) {
			LinkedList<Graph.GraphNode<V>> nextLv = new LinkedList<>();
			
			for (Graph.GraphNode<V> nodo: lv) {
				
				for (Graph.GraphNode<V> opposto: g.getOutNeighbors(nodo)) {
					if (opposto.state == Graph.GraphNode.Status.UNEXPLORED) {
						opposto.state = Graph.GraphNode.Status.EXPLORED;
						nextLv.add(opposto);
						opposto.timestamp = i;
					}
				}
			}
			
			lv = nextLv;
			i++;
		}
		
		for (Graph.GraphNode<V> nodo: g.getNodes()) {
			if (nodo.timestamp <= k)
				System.out.print(nodo.value + " ");
		}
		System.out.println();	
		
	}

	//public static <V> void kdist(Graph<V> g, Graph.GraphNode<V> source, int k) {
		//g.resetStatus();
		//for (Graph.GraphNode<V> nodo: g.getNodes()) {
			//nodo.timestamp = 666;
		//}
		//int i = 0;
		
		//LinkedList<Graph.GraphNode<V>> livello = new LinkedList<Graph.GraphNode<V>>();
		
		//livello.add(source);
		//source.state = Graph.GraphNode.Status.EXPLORED;
		
		//while (!livello.isEmpty()) {
			//LinkedList<Graph.GraphNode<V>> nextLivello = new LinkedList<Graph.GraphNode<V>>();
			
			//for (Graph.GraphNode<V> corrente: livello) {
				//corrente.timestamp = i;
				
				//for (Graph.GraphNode<V> opposto: g.getOutNeighbors(corrente)) {
					//if (opposto.state == Graph.GraphNode.Status.UNEXPLORED) {
						//opposto.state = Graph.GraphNode.Status.EXPLORED;
						//nextLivello.add(opposto);
					//}
				//}
			//}
			//i++;
			//livello = nextLivello;
			
		//}
		//String ris = "";
		//for (Graph.GraphNode<V> nodo: g.getNodes()) {
			//if (nodo.timestamp <= k) 
				//ris += nodo.value + " ";
		//}
		//System.out.println(ris);
	//}

}
