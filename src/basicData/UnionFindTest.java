package basicData;

import java.util.Random;

public class UnionFindTest {
    public static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++)
            uf.unionElements(random.nextInt(size), random.nextInt(size));

        for (int i = 0; i < m; i++)
            uf.isConnected(random.nextInt(size), random.nextInt(size));
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;

        UnionFind1 unionFind1 = new UnionFind1(size);
        UnionFind2 unionFind2 = new UnionFind2(size);
        UnionFind3 unionFind3 = new UnionFind3(size);
        UnionFind4 unionFind4 = new UnionFind4(size);
        UnionFind5 unionFind5 = new UnionFind5(size);
        UnionFind6 unionFind6 = new UnionFind6(size);

//        System.out.println("QuickFind: " + UnionFindTest.testUF(unionFind1, m));
//        System.out.println("QuickUnion1: " + UnionFindTest.testUF(unionFind2, m));
        System.out.println("QuickUnion2: " + UnionFindTest.testUF(unionFind3, m));
        System.out.println("QuickUnion3: " + UnionFindTest.testUF(unionFind4, m));
        System.out.println("QuickUnion4: " + UnionFindTest.testUF(unionFind5, m));
        System.out.println("QuickUnion5: " + UnionFindTest.testUF(unionFind6, m));
    }
}
