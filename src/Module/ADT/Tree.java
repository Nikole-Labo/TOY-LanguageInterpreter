package Module.ADT;

import java.util.ArrayList;
import java.util.List;

public class Tree<T>
{
    private Node<T> root;

    public Tree() {
        root=null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void inorderTraversal(List<T> list, Node node)
    {
        if (node == null) return ;
        inorderTraversal(list, node.left);
        list.add((T) node.value);
        inorderTraversal(list, node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        List<T> list = new ArrayList<T>();
        inorderTraversal(list, root);
        return list.toString();
    }
}