import java.util.*;
import java.io.*;

public class GraphServices<V> extends Graph<V> {

	public static <V> void sweep(Graph<V> g) {
		for (GraphNode<V> nodo: g.getNodes()) {
			nodo.state = GraphNode.Status.UNEXPLORED;
		}
		for (GraphNode<V> nodo: g.getNodes()) {
			if (nodo.state == GraphNode.Status.UNEXPLORED) {
				LinkedList<GraphNode<V>> known = new LinkedList<GraphNode<V>>();
				DFS_sweep(nodo, known);
			}
		}	  
	}
		
	public static <V> void DFS_sweep(Graph.GraphNode<V> nodo, LinkedList<Graph.GraphNode<V>> known) {
		nodo.state = GraphNode.Status.EXPLORING;
		known.add(nodo);	
		
		for (GraphNode<V> current: nodo.outEdges) {
		
			if (current.state == GraphNode.Status.UNEXPLORED) 
				System.out.println(nodo.value + " -> " + current.value + ": TREE");
			else if (current.state == GraphNode.Status.EXPLORING) 
				System.out.println(nodo.value + " -> " + current.value + ": BACK");
			else if (known.contains(current))
				System.out.println(nodo.value + " -> " + current.value + ": FORWARD");
			else
				System.out.println(nodo.value + " -> " + current.value + ": CROSS");
			if (current.state == GraphNode.Status.UNEXPLORED)	
				DFS_sweep(current, known);
		}
		nodo.state = GraphNode.Status.EXPLORED;	
	}
	
	public static <V> void topologicalSort(Graph<V> g) {
		g.resetStatus();
		boolean ciclo = false;
		
		for (GraphNode<V> nodo: g.getNodes()) {
			if (nodo.state == Graph.GraphNode.Status.UNEXPLORED) {
				ciclo = DFS_ciclo(nodo);
				if (ciclo) {
					System.out.println("Grafo in input non è un DAG");
					System.out.println();
					return;
				}
			}
		}
		g.resetStatus();
		LinkedList<Graph.GraphNode<V>> topo = new LinkedList<Graph.GraphNode<V>>();
		
		for (GraphNode<V> nodo: g.getNodes()) {
			if (nodo.state == Graph.GraphNode.Status.UNEXPLORED) {
				DFS_top(nodo, topo);
			}
		}	
		System.out.println("Ordinamento topologico");
		print(topo);	
	}
	
	public static <V> void DFS_top(Graph.GraphNode<V> nodo, LinkedList<Graph.GraphNode<V>> topo) {
		nodo.state = Graph.GraphNode.Status.EXPLORING;
		
		for (GraphNode<V> current: nodo.outEdges) {
			if (current.state == Graph.GraphNode.Status.UNEXPLORED)
				DFS_top(current, topo);
		} 
		topo.addFirst(nodo);
		nodo.state = Graph.GraphNode.Status.EXPLORED;
		
	}
	
	public static <V> boolean DFS_ciclo(Graph.GraphNode<V> nodo) {
		if (nodo.state == Graph.GraphNode.Status.EXPLORING) 
			return true;	
		
		if (nodo.state == Graph.GraphNode.Status.EXPLORED) 
			return false;
			
		boolean ris = false;	
		nodo.state = Graph.GraphNode.Status.EXPLORING;
		for (GraphNode<V> current: nodo.outEdges) 
			ris = ris || DFS_ciclo(current);
		
		nodo.state = Graph.GraphNode.Status.EXPLORED;
		return ris;
	}
		
	public static <V> void strongConnectedComponents(Graph<V> g) {
		g.resetStatus();
		
		for (GraphNode<V> nodo: g.getNodes()) {
			if (nodo.state == Graph.GraphNode.Status.UNEXPLORED) {
				LinkedList<Graph.GraphNode<V>> comp = new LinkedList<Graph.GraphNode<V>>();
				strongCC(nodo, comp);
				System.out.println("Sottografo fortemente connesso");
				print(comp);
			}
		}	
	}
	
	public static <V> void strongCC(Graph.GraphNode<V> nodo, LinkedList<Graph.GraphNode<V>> comp) {
		LinkedList<Graph.GraphNode<V>> out = new LinkedList<Graph.GraphNode<V>>();	//grafo
		DFS_out(nodo, out);		//V ->
		
		for(GraphNode<V> current: out)
			current.state = Graph.GraphNode.Status.UNEXPLORED;
		
		LinkedList<Graph.GraphNode<V>> in = new LinkedList<Graph.GraphNode<V>>();	//g_trasposto
		DFS_in(nodo, in);		//V <-
		
		for (GraphNode<V> current: in) {		//trova intersezione
			if (out.contains(current))
				comp.add(current);
			else 
				current.state = Graph.GraphNode.Status.UNEXPLORED;
		}
	}	 
	
	public static <V> void DFS_out(Graph.GraphNode<V> nodo, LinkedList<Graph.GraphNode<V>> known) {	
		if (nodo.state == GraphNode.Status.EXPLORING || nodo.state == GraphNode.Status.EXPLORED)
			return;
			
		nodo.state = GraphNode.Status.EXPLORING;
		known.add(nodo);
		
		for (GraphNode<V> current : nodo.outEdges) 
			DFS_out(current, known);
		
		nodo.state = GraphNode.Status.EXPLORED;
	}
	
	public static <V> void DFS_in(Graph.GraphNode<V> nodo, LinkedList<Graph.GraphNode<V>> known) {
		if (nodo.state == GraphNode.Status.EXPLORING || nodo.state == GraphNode.Status.EXPLORED)
			return;
		
		nodo.state = GraphNode.Status.EXPLORING;
		known.add(nodo);
		
		for (GraphNode<V> current : nodo.inEdges) 
			DFS_in(current, known);
		
		nodo.state = GraphNode.Status.EXPLORED;
	}
	
	public static <V> void print(LinkedList<Graph.GraphNode<V>> lista) {
		for (GraphNode<V> nodo: lista) {
			System.out.print(nodo.value + " ");
		}
		System.out.println();
	}
}
