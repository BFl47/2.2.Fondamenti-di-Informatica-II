import java.io.File;

public class Driver {

    public static void print() {
        System.out.println("Richiesto argomento: {graph, input, count_comp, get_comp}");
    }

    public static void main(String[] argv) {

        if (argv.length < 1) {
            print();
            return;
        }

        if (argv[0].equals("graph")) {
            Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo iniziale");
            System.out.println(graph.printAdj());

            System.out.println("Rimozione " + f + "," + c);
            graph.removeEdge(f,c);
            System.out.println(graph.printAdj());

            System.out.println("Rimozione " + c + "," + b);
            graph.removeEdge(c,b);
            System.out.println(graph.printAdj());

            System.out.println("Rimozione " + d + "," + c);
            graph.removeEdge(d,c);
            System.out.println(graph.printAdj());

            System.out.println("Aggiunta " + c + "," + f);
            graph.addEdge(c,f);
            System.out.println(graph.printAdj());

            System.out.println("Rimozione " + d + "," + c);
            graph.removeEdge(d,c);
            System.out.println(graph.printAdj());

            System.out.println("Rimozione nodo" + d);
            graph.removeNode(d);
            System.out.println(graph.printAdj());

            System.out.println("Rimozione nodo" + f);
            graph.removeNode(f);
            System.out.println(graph.printAdj());

            System.out.println("Rimozione nodo" + c);
            graph.removeNode(c);
            System.out.println(graph.printAdj());
        }
        else if(argv[0].equals("input")) {
        	File f = new File("graph.in");
        	Graph<String> g = Graph.readFF(f);
        	System.out.println("Rappresentazione ad archi:");
        	System.out.println(g);
        	System.out.println("Rappresentazione adiacenze:");
        	System.out.println(g.printAdj());
        }
        else if(argv[0].equals("count_comp")) {
        	Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");
            Graph.GraphNode<String> i = graph.addNode("i");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo:");
            System.out.println(graph.printAdj());
            System.out.println("Il grafo ha " + graph.nConComp() + " componenti connesse."); 
        }
        else if(argv[0].equals("get_comp")) {
        	Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");
            Graph.GraphNode<String> i = graph.addNode("i");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo:");
            System.out.println(graph.printAdj());
            int k = 1;
            for(Graph<String> sub : graph.getConComp()) {
            	System.out.println("Sottografo " + k);
            	System.out.println(sub.printAdj());
            	k++;
            }
        }
    }
}
