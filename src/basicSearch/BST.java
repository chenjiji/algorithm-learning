package basicSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private Node root;

    public BST() {
        this.root = null;
    }

    public int size() {
        return this.size;
    }

    public Node getRoot() {
        return this.root;
    }

    // 递归-添加
    public void add(E e) {
        this.root = add(this.getRoot(), e);
    }

    private Node add(Node root, E e) {
        if (root == null) {
            root = new Node(e);
            this.size++;
            return root;
        }

        if (root.e.compareTo(e) > 0)
            root.left = add(root.left, e);
        else if (root.e.compareTo(e) < 0)
            root.right = add(root.right, e);

        return root;
    }

    // 非递归-添加
    public void add2(E e) {
        Node current = this.getRoot();

        if (current == null) {
            this.root = new Node(e);
            this.size++;
            return;
        }

        while (current != null) {
            if (current.e.compareTo(e) > 0) {
                if (current.left == null) {
                    current.left = new Node(e);
                    this.size++;
                    break;
                }

                current = current.left;
            } else if (current.e.compareTo(e) < 0) {
                if (current.right == null) {
                    current.right = new Node(e);
                    this.size++;
                    break;
                }

                current = current.right;
            } else
                break;

        }
    }

    // 递归-打印
    @Override
    public String toString() {
        StringBuilder str = toString(new StringBuilder(), this.getRoot());

        return "打印输出: " + str.toString();
    }

    private StringBuilder toString(StringBuilder str, Node root) {
        if (root.left != null)
            str = toString(str, root.left);

        str.append(root.e + " ");

        if (root.right != null)
            str = toString(str, root.right);

        return str;
    }

    // 递归-前序遍历
    public void preOrder() {
        System.out.print("前序遍历: ");
        preOrder(this.getRoot());
    }

    public void preOrder(Node root) {
        if (root == null)
            return;

        System.out.print(root.e + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // 非递归-前序遍历
    public void preOrder2() {
        Stack<Node> stack = new Stack();
        stack.push(this.getRoot());
        System.out.print("前序遍历: ");

        do {
            Node node = stack.pop();

            System.out.print(node.e + " ");

            if (node.right != null)
                stack.push(node.right);

            if (node.left != null)
                stack.push(node.left);
        } while (!stack.isEmpty());
    }

    // 递归-中序遍历（有序遍历）
    public void inOrder() {
        System.out.print("中序遍历: ");
        inOrder(this.getRoot());
    }

    public void inOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.e + " ");
        inOrder(root.right);
    }

    // 递归-后序遍历
    public void postOrder() {
        System.out.print("后序遍历: ");
        postOrder(this.getRoot());
    }

    public void postOrder(Node root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.e + " ");
    }

    // 层序遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.getRoot());
        System.out.print("层序遍历: ");

        do {
            Node node = queue.poll();

            System.out.print(node.e + " ");

            if (node.left != null)
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);
        } while (!queue.isEmpty());
    }

    // 递归-判断值是否存在
    public boolean contain(E e) {
        return contain(this.getRoot(), e);
    }

    public boolean contain(Node root, E e) {
        if (root != null) {
            if (root.e.compareTo(e) == 0)
                return true;
            else if (root.e.compareTo(e) > 0)
                return contain(root.left, e);
            else
                return contain(root.right, e);
        }

        return false;
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] arr = new int[]{5, 3, 6, 8, 4, 2};

        for (int i : arr) {
            bst.add(i);
//            bst.add2(i);
        }

//        System.out.println(bst.size() + "--" + bst.contain(7) + " " + bst.contain(4));
//        bst.preOrder();
//        System.out.println(" ");
//        bst.inOrder();
//        System.out.println(" ");
//        bst.postOrder();
//        System.out.println(" ");
//        System.out.println(bst);
//        bst.preOrder2();
        bst.levelOrder();
    }
}
