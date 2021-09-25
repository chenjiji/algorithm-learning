package basicData;

import leetCode.FileOperation;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
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

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;

        return getHeight(node.left) - getHeight(node.right);
    }

    // 获得节点node的高度
    private int getHeight(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    private Node leftRotate(Node y) {
        Node x = y.right;
        Node left = x.left;
        x.left = y;
        y.right = left;

        // * 这里得注意顺序，y原本是根节点，x为其下子节点，经过旋转，x为根节点，y为子节点，所以得先更新y的高度，再更新x的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node right = x.right;
        x.right = y;
        y.left = right;

        // * 这里得注意顺序，y原本是根节点，x为其下子节点，经过旋转，x为根节点，y为子节点，所以得先更新y的高度，再更新x的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    @Override
    public void add(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("The key cannot be empty.");

        root = add(root, key, value);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 递归判断各个节点的平衡因子是否大于1
    private boolean isBalanced(Node node) {
        // 节点为空，必然平衡
        if (node == null)
            return true;

        // 判断当前节点的平衡因子是否大于1，大于1则不平衡
        if (Math.abs(getBalanceFactor(node)) > 1)
            return false;

        // 往下遍历，只要有一个子节点不平衡，返回false，传递至根节点返回结果
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();

        inOrder(root, keys);

        // 判断keys是否为升序
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0)
                return false;

        return true;
    }

    // 中序遍历，保持至Keys
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
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

        return toBalance(node);
    }

    private Node toBalance(Node node) {
        if (node == null)
            return null;

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balancedFactor = getBalanceFactor(node);

        // LL
        if (balancedFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balancedFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balancedFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balancedFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

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
            node = rightNode;
        } else
            node.left = removeMin(node.left);

        return toBalance(node);
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
                node = null;
            } else if (node.left != null && node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                node = leftNode;
            } else {
                Node successor = minimum(node.right);
//                successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                node = successor;
            }
        }

        return toBalance(node);
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

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("/Users/chenhuiqiong/Documents/workspace/intelliJ/algorithm-learning/resources/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }
}
