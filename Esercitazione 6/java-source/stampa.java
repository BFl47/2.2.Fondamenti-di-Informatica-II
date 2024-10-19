
void print(){
	System.out.print("{ ");
	print_aux(this.root);
	System.out.println("}");
}

void print_aux(Node n) {
	System.out.print(n + " ");
	if (n.left != null ) {
		System.out.print("{ ");
		print_aux(n.left);
		System.out.print(" } ");
	}
	
	if (n.right != null ) {
		System.out.print(" { ");	
		print_aux(n.right);	
		System.out.print(" } ");			
	}
}
