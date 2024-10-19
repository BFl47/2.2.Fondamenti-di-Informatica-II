public class Tree {

    private int key;
    private Tree left;
    private Tree right;

    public Tree(int key) {
        this.key = key;
    }

    public static Tree build_1() {
        Tree n6 = new Tree(6);
        Tree n3 = new Tree(3);
        Tree n12 = new Tree(12);
        Tree n1 = new Tree(1);
        Tree n5 = new Tree(5);
        Tree n7 = new Tree(7);
        Tree n15 = new Tree(15);

        n6.left = n3;
        n6.right = n12;

        n3.left = n1;
        n3.right = n5;

        n12.left = n7;
        n12.right = n15;

        return n6;
    }

    public static Tree build_2() {
        Tree n6 = new Tree(6);
        Tree n3 = new Tree(3);
        Tree n12 = new Tree(12);
        Tree n1 = new Tree(1);
        Tree n5 = new Tree(5);
        Tree n7 = new Tree(7);
        Tree n15 = new Tree(15);

        n6.right = n3;
        n6.left = n12;

        n3.left = n1;
        n3.right = n5;

        n12.left = n7;
        n12.right = n15;

        return n6;
    }

    public static Tree build_3() {
        Tree n6 = new Tree(6);
        Tree n3 = new Tree(3);
        Tree n12 = new Tree(12);
        Tree n1 = new Tree(1);
        Tree n5 = new Tree(5);

        n6.left = n3;

        n3.left = n1;
        n3.right = n5;

        n5.right = n12;

        return n6;
    }

    public boolean isBST() {
        if (left == null && right == null)
			return true;
		else if (left != null && right == null)
			return (key > left.key) && left.isBST();
		else if (left == null && right != null)
			return (key < right.key) && right.isBST();
		else
			return (key > left.key) && (key < right.key) && left.isBST() && right.isBST();
    }

    public boolean isBalanced() {
		if (left == null && right == null)
			return true;
        if (Math.abs(altezza(left) - altezza(right)) > 1)
			return false;
		else
			return true && left.isBalanced() && right.isBalanced();	
    }
	
	public int altezza(Tree t) {
		if (t == null)
			return 0;
		if (t.left == null && t.right == null)
			return 0;
		return 1 + Math.max(altezza(t.left), altezza(t.right));
	}
	
    public boolean isAVL() {
        if (left == null && right == null)
			return true;
		if (Math.abs(altezza(left) - altezza(right)) <= 1) {
			if (left != null && right == null)
				return (key > left.key) && left.isAVL();
			else if (left == null && right != null)
				return (key < right.key) && right.isAVL();
			else
				return (key > left.key) && (key < right.key) && left.isAVL() && right.isAVL();
		}
		else return false;	
    }
    /*
    public boolean isAVL() {
		if (isBST() && isBalanced())
				return true;
			return false;
	}
	*/	
}
