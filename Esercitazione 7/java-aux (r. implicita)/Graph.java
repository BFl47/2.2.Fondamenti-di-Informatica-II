import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Graph<V> {
	LinkedList<GraphNode<V>> listaNodi;
	
    public Graph () {
		listaNodi = new LinkedList<GraphNode<V>>();
	}

    public List<GraphNode<V>> getNodes() {
        return (LinkedList<GraphNode<V>>) listaNodi;
    }

    public List<GraphNode<V>> getNeighbors(GraphNode<V> n){
		return (LinkedList<GraphNode<V>>) n.outEdges;			
    }

    public GraphNode addNode(V value) {
        GraphNode nuovo = new GraphNode(value);
        listaNodi.add(nuovo);
        return nuovo;
    }

    public void addEdge(GraphNode<V> s, GraphNode<V> t) {
        s.outEdges.add(t);
        t.outEdges.add(s);
    }

    public V getNodeValue(GraphNode<V> n) {
        return n.value;
    }
	
    public void removeEdge(GraphNode<V> v1, GraphNode<V> v2){
		v1.outEdges.remove(v2);
		v2.outEdges.remove(v1);
    }

    public void removeNode(GraphNode<V> v){
		for (GraphNode vicino : v.outEdges) 
			vicino.outEdges.remove(v);
		listaNodi.remove(v);
    }

    public String printAdj() {
		String ris = "";
		for (GraphNode<V> n : listaNodi) {
			ris += n.value + "|--> ";
			for (GraphNode<V> vicino : n.outEdges) {
				ris += vicino.value + " ";
			}
			ris += "\n";
		}
		return ris;
    }


    @Override
    public String toString(){
      return null;
    }
    
    public static <V> Graph<V> readFF(File input) {
		Graph<V> ris = new Graph();
		int nNodi, nArchi;
		V v1, v2;
		try {
			FileInputStream f = new FileInputStream(input);
			Scanner sc = new Scanner(f);
			String s;
			boolean prima = true;
			
			while (sc.hasNextLine()) {
				s = sc.nextLine();
				String[] riga = s.split(" ");
				
				if (prima) {
					nNodi = Integer.parseInt(riga[0]);
					nArchi = Integer.parseInt(riga[1]);
					prima = false;
					System.out.println("nodi: " + nNodi + " archi: " + nArchi);
					
					for (int i = 1; i <= nNodi; i++) {
						V valore = (V) (i+"");
						ris.addNode(valore);
					}
					continue;
				}
				v1 = (V) (riga[0]);
				v2 = (V) (riga[1]);
				for (GraphNode n1 : ris.listaNodi) {
					for (GraphNode n2 : ris.listaNodi) {
						if (ris.getNodeValue(n1).equals(v1) && ris.getNodeValue(n2).equals(v2)) 
							ris.addEdge(n1, n2);
					}
				}	
			}
			f.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
      return ris;
    }
    
    public void DFS(Graph<V> g, GraphNode<V> nodo, Set<GraphNode<V>> known) {
		nodo.state = GraphNode.Status.EXPLORED;
		known.add(nodo);
		for (GraphNode<V> vicino : nodo.outEdges) {
			if (vicino.state == GraphNode.Status.UNEXPLORED) {
				DFS(g, vicino, known);
			}
		}	
	}

    public int nConComp(){	
		List<Graph<V>> foresta = (LinkedList)this.getConComp();	
		return foresta.size();
	}

    public List<Graph<V>> getConComp(){
		List<Graph<V>> foresta = new LinkedList<Graph<V>>();
		
		for (GraphNode nodo : listaNodi) {
			nodo.state = Graph.GraphNode.Status.UNEXPLORED;
		}
		for (GraphNode<V> nodo: listaNodi) {
			Graph<V> comp = new Graph();
			
			if (nodo.state == GraphNode.Status.UNEXPLORED) {
				HashSet<GraphNode<V>> known = new HashSet<GraphNode<V>>();
				DFS(this, nodo, known);
				
				for (GraphNode<V> n: known) {
					GraphNode<V> nuovo = comp.addNode(n.value);
					nuovo.outEdges = n.outEdges;	
				}
				foresta.add(comp);
			}
		}
		return foresta;
    }

	public static class GraphNode<V> implements Cloneable{

        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<GraphNode<V>> outEdges;

		public GraphNode(V value) {
			this.value = value;
			this.outEdges = new LinkedList<GraphNode<V>>();
		}
        // keep track status
        protected Status state;

		@Override
		public String toString() {
			return "GraphNode [value=" + value + ", state=" + state + "]";
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}	
    }
}
