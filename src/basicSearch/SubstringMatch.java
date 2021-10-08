package basicSearch;

import leetCode.FileOperation;

public class SubstringMatch {
    private SubstringMatch() {
    }

    public static int bruteforce(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen < tLen) return -1;

        // [0, sLen - tLen]
        for (int i = 0, j = sLen - tLen; i <= j; i++) {
            int k = 0;


            for (; k < tLen; k++) {
                if (s.charAt(k + i) != t.charAt(k))
                    break;
            }

            if (k == tLen)
                return i;
        }

        return -1;
    }

    public static int rabinKarp(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen < tLen) return -1;

        int B = 256;
        long MOD = (long) (1e9 + 7);
        long tHash = t.charAt(0);
        long sHash = s.charAt(0);
        long P = 1;

        for (int i = 1; i < tLen; i++) {
            P = (P * B) % MOD;
            tHash = (tHash * B + t.charAt(i)) % MOD;
            sHash = (sHash * B + s.charAt(i)) % MOD;
        }

        if (tHash == sHash) return 0;

        for (int i = 1, j = sLen - tLen; i <= j; i++) {
            /**
             * 减去前一位
             * (hash + MOD) % MOD 参考时钟
             * 减法和求模运算混用时，会出现负数，补上一圈MOD，然后再作取模(为了统一逻辑，防止当出现正数时，加多了MOD)，就能得到正确结果
             * 好比时钟，3点 - 8小时 = 昨天19点 => 3 + 24 - 8 = 19
             */
            sHash = ((sHash - s.charAt(i - 1) * P) % MOD + MOD) % MOD;
            // 加上后一位
            sHash = (sHash * B + s.charAt(i + tLen - 1)) % MOD;

            if (sHash == tHash && equal(s, t, i)) return i;
        }

        return -1;
    }

    private static boolean equal(String s, String t, int l) {
        for (int i = 0, j = t.length(); i < j; i++) {
            if (t.charAt(i) != s.charAt(i + l)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "hello, this is liuyubobobo.";
        String t1 = "bo";
        SubstringMatchHelper.matchTest("bruteforce", s1, t1);
        SubstringMatchHelper.matchTest("rk", s1, t1);

        String s2 = FileOperation.readFile("/Users/chenhuiqiong/Documents/workspace/intelliJ/algorithm-learning/resources/pride-and-prejudice.txt");
        String t2 = "china";
        SubstringMatchHelper.matchTest("bruteforce", s2, t2);
        SubstringMatchHelper.matchTest("rk", s2, t2);

        SubstringMatchHelper.matchTest("bruteforce", s2, "zyx");
        SubstringMatchHelper.matchTest("rk", s2, "zyx");

        /// Worst case
        int n = 1000000, m = 10000;

        StringBuilder s3 = new StringBuilder();
        for (int i = 0; i < n; i++)
            s3.append('a');

        StringBuilder t3 = new StringBuilder();
        for (int i = 0; i < m - 1; i++)
            t3.append('a');
        t3.append('b');

        SubstringMatchHelper.matchTest("bruteforce", s3.toString(), t3.toString());
        SubstringMatchHelper.matchTest("rk", s3.toString(), t3.toString());
    }
}
