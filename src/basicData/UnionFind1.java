package basicData;

// Quick Find
public class UnionFind1 implements UF {
    private int[] id;


    public UnionFind1(int size) {
        id = new int[size];

        // 初始化, 每一个id[i]指向自己, 没有合并的元素
        for (int i = 0; i < size; i++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int get(int index) {
        return id[index];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return get(p) == get(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = get(p);
        int qID = get(q);

        if (pID == qID)
            return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
    }
}
