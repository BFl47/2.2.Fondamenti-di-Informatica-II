
class BubbleSort {
	
	void bubbleSort(int a[]) {
		int n = a.length;
		for (int i = 0; i < n-1; i++)
			for (int j = 0; j < n-i-1; j++)
				if (a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
	}
	
	//Stampa array
	void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i) 
			System.out.print(arr[i] + " ");
		System.out.println();
	}	
	
	//Driver per provare la classe
	public static void main (String[] args) {
		if (args[0].equals("rnd")) {
			int len = Integer.parseInt(args[1]);
			int arr[] = new int[len];
			for (int i = 0; i < len; i++)
				arr[i] = (int)(Math.random()*10);
			
			BubbleSort ob = new BubbleSort();
			ob.bubbleSort(arr);
			
			System.out.println("Sorted array random");
			ob.printArray(arr);
		} else {
			int arr[] = new int[args.length];
			for (int i = 0; i < args.length; i++) 
				arr[i] = Integer.parseInt(args[i]);
			BubbleSort ob = new BubbleSort();
			ob.bubbleSort(arr);
			
			System.out.println("Sorted array args");
			ob.printArray(arr);
		}		
		//int arr[] = {64, 34, 25, 12, 22};
	}
}

