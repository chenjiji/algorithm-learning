package basicData;

// Quick Union，基于rank(层数)的优化，连接时，层数多的作为根，可以减少深度，避免最坏情况下，成为链表的情况
public class UnionFind4 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int index) {
        while (index != parent[index]) {
            index = parent[index];
        }

        return index;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else {
            parent[pRoot] = qRoot;
            rank[qRoot]++;
        }
    }
}
