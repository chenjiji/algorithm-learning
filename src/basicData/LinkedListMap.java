package basicData;

import leetCode.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key) {
        Node curr = dummyHead.next;

        while (curr != null) {
            if (curr.key.equals(key))
                return curr;

            curr = curr.next;
        }

        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);

        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else
            node.value = value;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;

        while (prev.next != null) {
            Node curr = prev.next;

            if (curr.key.equals(key)) {
                prev.next = curr.next;
                V v = curr.value;

                curr = null;
                size--;
                return v;
            }

            prev = prev.next;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        Node node = getNode(key);

        return node != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);

        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);

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

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        LinkedListMap<Integer, Integer> map = new LinkedListMap<>();

        for (int i : nums)
            map.add(i, i);

        System.out.println(map.remove(2));
        System.out.println(map.remove(4));

        System.out.println(map);

//        System.out.println("Pride and Prejudice");
//
//        ArrayList<String> words = new ArrayList<>();
//
//        if (FileOperation.readFile("/Users/chenhuiqiong/Documents/workspace/intelliJ/algorithm-learning/resources/pride-and-prejudice.txt", words)) {
//            System.out.println("Total words: " + words.size());
//
//            LinkedListMap<String, Integer> map = new LinkedListMap<>();
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
