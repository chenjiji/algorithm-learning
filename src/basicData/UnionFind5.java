package basicData;

/**
 * Quick Union，基于rank，并且加上路径压缩优化
 * 因为加上路径压缩后，已经能极大限度的降低树的高度
 * rank只是在连接时，起到辅助的作用，所以不用find都去都去维护rank值
 * 只用在连接时维护，且仍能起到减小高度的作用
 */
public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind5(int size) {
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
            // 当前节点指向他父亲的父亲节点，从而减小一层的高度
            parent[index] = parent[parent[index]];
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
