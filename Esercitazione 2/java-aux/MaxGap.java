
public class MaxGap {

   public static int maxGap(int[] array, int start, int end) {
        return maxGap_aux(array, start, end-1);
    }
    public static int maxGap_aux(int[] array, int start, int end) {
        if (end-start == 1)
            return array[end] - array[start];
      	int mid = (start + end) / 2;
        int dx = maxGap_aux(array, start, mid);
        int sx = maxGap_aux(array, mid, end);
        if (sx >= dx)
            return sx;
        else
            return dx;
    }

}
