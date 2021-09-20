package basicData;

// Quick Union
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++)
            parent[i] = i;
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
        int pParent = find(p);
        int qParent = find(q);

        if (pParent == qParent)
            return;

        parent[pParent] = qParent;
    }
}
