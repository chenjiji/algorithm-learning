package basicData;

/**
 * Quick Union，基于rank，并且加上路径压缩优化
 * 因为加上路径压缩后，已经能极大限度的降低树的高度
 * rank只是在连接时，起到辅助的作用，所以不用find都去都去维护rank值
 * 只用在连接时维护，且仍能起到减小高度的作用
 */
public class UnionFind6 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
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

    /**
     * 依旧是压缩路径的思路，但采用递归实现
     * 在find的过程中，将该节点所属的根节点下所有的子节点，都直接指向根
     */
    private int find(int index) {
        // 如果该节点指向的不是自身，则证明不是根节点
        if (index != parent[index])
            // 将当前节点，指向递归函数返回的根节点
            parent[index] = find(parent[index]);

        // 当前节点指向递归返回的根节点，此处返回的就是根节点
        // 或者一直递归到底后，未进入上边的if条件，证明已找到根节点，作为返回值返回
        return parent[index];
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
