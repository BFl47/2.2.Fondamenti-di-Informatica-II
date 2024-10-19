import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Graph<V> {
	LinkedList<GraphNode<V>> listaNodi;
	LinkedList<Arco<V>> listaArchi;
	
    public Graph () {
		listaNodi = new LinkedList<GraphNode<V>>();
		listaArchi = new LinkedList<Arco<V>>();
	}

    public List<GraphNode<V>> getNodes() {
        return (LinkedList<GraphNode<V>>) listaNodi;
    }
    
    public LinkedList<Arco<V>> getArchi() {
		return listaArchi;
	}

    public List<GraphNode<V>> getNeighbors(GraphNode<V> n){
		LinkedList<GraphNode<V>> ris = new LinkedList<GraphNode<V>>();
		for (Arco<V> arco: listaArchi) 
			ris.add(opposite(n, arco));
		return ris;
    }
	
	public GraphNode<V> opposite(GraphNode<V> u, Arco<V> e) {
		if (u == e.origine)
			return e.destinazione;
		else if (u == e.destinazione)
			return e.origine;
		else
			return null;
	}
	
    public GraphNode addNode(V value) {
        GraphNode nuovo = new GraphNode(value);
        listaNodi.add(nuovo);
        return nuovo;
    }

    public void addEdge(GraphNode<V> s, GraphNode<V> t) {
		Arco<V> arco = new Arco(s, t, 1);
        s.outEdges.add(arco);
        t.outEdges.add(arco);
        listaArchi.add(arco);
    }
	
	public Arco<V> getEdge(GraphNode<V> u, GraphNode<V> v) {
		for (Arco<V> arco: u.outEdges) {
			if (arco.origine == u && arco.destinazione == v || 
			arco.origine == v && arco.destinazione == u)
				return arco;
		}
		return null;
	}
	public V getNodeValue(GraphNode<V> n) {
		return n.value;
    }
    
    public void removeEdge(GraphNode<V> u, GraphNode<V> v) {
		Arco<V> arco = getEdge(u, v);
		u.outEdges.remove(arco);
		v.outEdges.remove(arco);
		listaArchi.remove(arco);
    }

    public void removeNode(GraphNode<V> v){
		for (Arco arco : v.outEdges) {
			GraphNode opposto = opposite(v, arco);
			opposto.outEdges.remove(arco);
			listaArchi.remove(arco);
		}
		listaNodi.remove(v);
    }

    public static <V> Graph<V> readFF(File input) {
		Graph<V> ris = new Graph();
		int nNodi, nArchi;
		V v1, v2, peso;
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
				peso = (V) (riga[2]);
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
			Graph<V> prova = new Graph();
			
			if (nodo.state == GraphNode.Status.UNEXPLORED) {
				HashSet<GraphNode<V>> known = new HashSet<GraphNode<V>>();
				DFS(this, nodo, known);
				
				for (GraphNode<V> n: known) {
					prova.listaNodi.add(n);
					
					for (Arco<V> arco: n.outEdges) 
						prova.listaArchi.add(arco);
				}
				foresta.add(prova);
			}
		}
		return foresta;
    }
    
    public void DFS(Graph<V> g, GraphNode<V> nodo, Set<GraphNode<V>> known) {
		nodo.state = GraphNode.Status.EXPLORED;
		known.add(nodo);
		for (Arco<V> arco : nodo.outEdges) {
			GraphNode<V> vicino = g.opposite(nodo, arco);
			if (vicino.state == GraphNode.Status.UNEXPLORED) {
				DFS(g, vicino, known);
			}
		}	
	}
	
	public String printAdj() {
		String ris = "";
		for (GraphNode<V> n : listaNodi) {
			ris += n.value + "|--> ";
			for (Arco<V> arco : n.outEdges) {
				GraphNode<V> vicino = opposite(n, arco);
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

	public static class GraphNode<V> implements Cloneable{

        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<Arco<V>> outEdges;

		public GraphNode(V value) {
			this.value = value;
			this.outEdges = new LinkedList<Arco<V>>();
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
    
    public static class Arco<V> {
		protected V peso;
		protected GraphNode<V> origine, destinazione;
		
		public Arco(GraphNode<V> u, GraphNode<V> v, V peso) {
			this.origine = u;
			this.destinazione = v;
			this.peso = peso;
		}
				
		@Override 
		public String toString() {
			return "Arco [origine=" + this.origine + ", dest=" + this.destinazione + "peso=" + this.peso;	
		}
	}
}
