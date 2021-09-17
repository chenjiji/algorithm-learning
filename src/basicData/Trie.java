package basicData;

import leetCode.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWords) {
            this.isWord = isWords;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private int size;
    private Node root;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public void add(String str) {
        int len = str.length();
        Node current = root;

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            if (current.next.get(c) == null)
                current.next.put(c, new Node());

            current = current.next.get(c);
        }

        if (current.isWord == false) {
            current.isWord = true;
            size++;
        }
    }

    public boolean contains(String str) {
        int len = str.length();
        Node current = root;

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            if (current.next.get(c) == null)
                return false;

            current = current.next.get(c);
        }

        return current.isWord;
    }

    public int getSize() {
        return size;
    }

    public boolean isPrefix(String prefix) {
        Node current = root;
        int len = prefix.length();

        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);

            if (current.next.get(c) == null)
                return false;

            current = current.next.get(c);
        }

        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");
        String filename = "/Users/chenhuiqiong/Documents/workspace/intelliJ/algorithm-learning/resources/pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ---

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
