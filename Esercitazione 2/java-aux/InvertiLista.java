
import java.util.*;

public class InvertiLista {

	public static void invertiLista(LinkedList<Integer> list) {
        invertiLista_aux(list, 0, list.size()-1);
	}
    public static void invertiLista_aux(LinkedList<Integer> list, int i, int j)  {
        if (i >= j)
            return;

        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);

        invertiLista_aux(list, i+1, j-1);
    }

}
