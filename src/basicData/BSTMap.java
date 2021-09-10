package basicData;

import java.util.ArrayList;

import leetCode.FileOperation;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null || key == null)
            return null;

        if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else
            return node;
    }

    @Override
    public void add(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be empty.");

        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else
            node.value = value;

        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);

        if (node == null)
            return null;

        root = remove(root, key);

        return node.value;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;

            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) > 0)
            node.right = remove(node.right, key);
        else if (key.compareTo(node.key) < 0)
            node.left = remove(node.left, key);
        else {
            if (node.left == null && node.right == null) {
                size--;
                return null;
            }

            if (node.left != null && node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }

        return node;
    }

    @Override
    public boolean contains(K key) {
        Node node = getNode(root, key);

        return node != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);

        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);

        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 递归-打印
    @Override
    public String toString() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is Empty.");
        }

        StringBuilder str = toString(new StringBuilder(), root);

        return "打印输出: " + str.toString();
    }

    private StringBuilder toString(StringBuilder str, Node node) {
        if (node.left != null)
            str = toString(str, node.left);

        str.append(node.key + " ");

        if (node.right != null)
            str = toString(str, node.right);

        return str;
    }

    public static void main(String[] args) {
        BSTMap<Integer, Integer> bst = new BSTMap<>();
        Integer[] arr = new Integer[]{5, 3, 6, 8, 4, 2};

        for (Integer i : arr) {
            bst.add(i, i);
        }

        System.out.println(bst);

        for (Integer i : new Integer[]{2, 3, 4, 5, 6}) {
            bst.remove(i);
            System.out.println(bst);
        }

//        System.out.println("Pride and Prejudice");
//
//        ArrayList<String> words = new ArrayList<>();
//
//        if (FileOperation.readFile("/Users/chenhuiqiong/Documents/workspace/intelliJ/algorithm-learning/resources/pride-and-prejudice.txt", words)) {
//            System.out.println("Total words: " + words.size());
//
//            BSTMap<String, Integer> map = new BSTMap<>();
//
//            for (String word : words) {
//                if (map.contains(word))
//                    map.set(word, map.get(word) + 1);
//                else
//                    map.add(word, 1);
//            }
//
//            System.out.println("Total different words: " + map.getSize());
//            System.out.println("Frequency of PRIDE: " + map.get("pride"));
//            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
//        }
//
//        System.out.println();
    }
}
