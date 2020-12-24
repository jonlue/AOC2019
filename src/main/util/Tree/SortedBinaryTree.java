package main.util.Tree;

public class SortedBinaryTree<T extends Comparable<T>> implements Tree<T> {
    protected TreeNode<T> root;

    private TreeNode<T> insertAt(TreeNode<T> node, T x) {
        if (node == null) {
            return new TreeNode<T>(x,0);
        } else {
            if (x.compareTo(node.element) <= 0) {
                node.left = insertAt(node.left, x);
            } else {
                node.right = insertAt(node.right, x);
            }
            return node;
        }
    }
    /*private TreeNode<T> insertAt(TreeNode<T> node, T x, T parent) {
        if (node == null) {
            return new TreeNode<T>(x);
        } else {
            if (parent.compareTo(node.element) == 0) {
                node.left = insertAt(node.left, x, parent);
            } else {
                node.right = insertAt(node.right, x, parent);
            }
            return node;
        }
    }*/
    private TreeNode<T> insertAt(TreeNode<T> node, T x, T parent, int height) {
        height++;
        if (node == null) {
            return new TreeNode<T>(x);
        } else {
            if(parent.compareTo(node.element) == 0) {
                if (node.left == null) {
                    node.left = new TreeNode<T>(x,height);
                } else {
                    node.right = new TreeNode<T>(x,height);
                }
            }else{
                insertAt(node.left,x,parent,height);
                insertAt(node.right,x,parent,height);
            }
            return node;
        }
    }

    public void add(T x) {
        root = insertAt(root, x);
    }
    public void add(T parent, T x){
        root = insertAt(root,x,parent,0);
    }


    // find node to replace
    private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
        if (node.right != null) {
            node.right = findRepAt(node.right,rep);
        } else {
            rep.element = node.element;
            node = node.left;
        }
        return node;
    }

    // remove node
    private TreeNode<T> removeAt(TreeNode<T> node, T x,TreeNode<T> removed ) {
        if (node == null) {
            return null;
        } else {
            if (x.compareTo(node.element) == 0) {
                // found
                removed.element = node.element;
                if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    node.left = findRepAt(node.left,node);
                }
            } else if (x.compareTo(node.element) < 0) {
                // search left
                node.left = removeAt(node.left, x, removed);
            } else {
                // search right
                node.right = removeAt(node.right, x, removed);
            }
            return node;
        }
    }

    public T remove(T x) {
        TreeNode<T> removed = new TreeNode<T>(null);
        root = removeAt(root, x, removed);
        return removed.element;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public Traversal<T> traversal() {
        return new TreeTraversal<>(root);
    }

    protected int calcHeight(TreeNode<T> node) {
        if(node == null){
            return 0;
        }else{
            return Math.max(calcHeight(node.left),calcHeight(node.right))+1;
        }
    }


    protected int calcSize(TreeNode p) {
        if(p==null){
            return 0;
        }else{
            return calcSize(p.left)+1+calcSize(p.right);
        }
    }

    public int height() {
        return calcHeight(root);
    }

    public int size() {
        return calcSize(root);
    }

    public boolean balanced() {
        throw new UnsupportedOperationException();
    }

    // only for testing and debugging purposes: show the structure of the tree
    public String printTree() {
        StringBuilder out = new StringBuilder();
        if (root.right != null) {
            printTree(root.right,out, true, "");
        }
        out.append(root.element+"\n");
        if (root.left != null) {
            printTree(root.left,out, false, "");
        }
        return out.toString();
    }

    public void printTree(TreeNode node, StringBuilder out, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, out, true,
                    indent + (isRight ? "        " : " |      "));
        }
        out.append(indent);
        if (isRight) {
            out.append(" /");
        } else {
            out.append(" \\");
        }
        out.append("----- ");
        out.append(node.element+"\n");
        if (node.left != null) {
            printTree(node.left, out, false,
                    indent + (isRight ? " |      " : "        "));
        }
    }
    public int getSumHeight(){
        return getSumHeight(root);
    }

    private int getSumHeight(TreeNode<T> node) {
        if(node == null){
            return 0;
        }else{
            return (getSumHeight(node.left)+getSumHeight(node.right)+node.height);
        }
    }

    public int getDistance(T position, T destination) {
        int a = getHeight(root, position)-1;
        int b = getHeight(root, destination)-1;
        T temp = getParentElement(root,position,destination);
        //System.out.println(temp);
        int c = getHeight(root,temp);
        //System.out.println(a);
        //System.out.println(b);
        //System.out.println(c);

        return  a+b - c*2;
    }

    public T getParentElement(TreeNode<T> node, T child1, T child2){
        if(node == null){
            return null;
        }

        if(node.left == null){
            return (T) getParentElement(node.right, child1, child2);
        }else if(node.right == null){
            return (T) getParentElement(node.left, child1, child2);
        }

        if((checkIsChild(node.left, child1) || checkIsChild(node.left, child2)) && (checkIsChild(node.right, child1) || checkIsChild(node.right, child2))){
            return node.element;
        }else{
            T temp1 = (T) getParentElement(node.right,child1,child2);
            T temp2 = (T) getParentElement(node.left,child1,child2);
            if(temp1 == null){
                return temp2;
            }else{
                return temp1;
            }
        }
    }

    private boolean checkIsChild(TreeNode<T> node, T name) {
        if(node == null){
            return false;
        }
        if(node.element.equals(name)){
            return true;
        }
        return checkIsChild(node.left,name) || checkIsChild(node.right,name);

    }

    private int getHeight(TreeNode<T> node, T name) {
        if(node == null){
            return 0;
        }
        if(node.element.equals(name)){
            return node.height;
        }
        return Math.max(getHeight(node.left,name),getHeight(node.right,name));

    }
}
