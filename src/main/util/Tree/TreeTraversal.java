package main.util.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
        inorder(root, vis);
    }
    private void inorder(TreeNode<T> node, Visitor<T> visitor){
        if(node != null){
            inorder(node.left,visitor);
            visitor.visit(node.element);
            inorder(node.right,visitor);
        }
    }

    public void preorder(Visitor<T> vis) {
        preorder(root,vis);
    }
    private void preorder(TreeNode<T> node, Visitor<T> visitor){
        if(node != null){
            visitor.visit(node.element);
            preorder(node.left,visitor);
            preorder(node.right,visitor);
        }
    }

    public void postorder(Visitor<T> vis) {
        postorder(root,vis);
    }
    private void postorder(TreeNode<T> node, Visitor<T> visitor){
        if(node != null){
            postorder(node.left,visitor);
            postorder(node.right,visitor);
            visitor.visit(node.element);
        }
    }

    
    @Override
    public void levelorder(Visitor<T> vis) {
        levelorder(root, vis);
    }
    private void levelorder(TreeNode<T> node, Visitor<T> visitor){
        Queue q = new ArrayDeque();
        if(node != null){q.add(node); q.offer(node);}
        while(!q.isEmpty()){
            q.remove();
            node = (TreeNode<T>) q.poll();
            visitor.visit(node.element);
            if(node.left!= null){q.add(node.left);q.offer(node.left);}
            if(node.right!= null){q.add(node.right);q.offer(node.right);}
        }
    }
    @Override
    public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> vis){
        interval(root,min,max,vis);
    }
    private void interval(TreeNode<T> node, Comparable<T> min, Comparable<T> max, Visitor<T> visitor){
        if(node != null) {
            if(min.compareTo(node.getValue())<=0 && max.compareTo(node.getValue()) >=0){
                visitor.visit(node.element);
            }
            if (min.compareTo(node.getValue()) < 0) {
                interval(node.left, min, max, visitor);
            }
            if (max.compareTo(node.getValue()) > 0) {
                interval(node.right, min, max, visitor);
            }
        }
    }
}
