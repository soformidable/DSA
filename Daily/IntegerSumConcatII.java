import java.util.*;

public class IntegerSumConcatII{
    private static final int MOD = 1000000007;
    private static final int MAX = 100001;
    private static final int[] pow = new int[MAX];

    static {
        pow[0] = 1;
        for (int i = 1; i < MAX; i++)
            pow[i] = (int) ((pow[i - 1] * 10L) % MOD);
    }

    public static int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] A = new int[n + 1];
        int[] B = new int[n + 1];
        int[] len = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';            
            A[i + 1] = A[i] + d;
            
            if (d > 0) {
                B[i + 1] = (int) ((B[i] * 10L + d) % MOD);
                len[i + 1] = len[i] + 1;
            } else {
                B[i + 1] = B[i];
                len[i + 1] = len[i];
            }
        }

        int[] res = new int[queries.length];
        int i = 0;

        for (int[] q : queries) {
            int l = q[0], r = q[1] + 1;
            
            long sub = ((long) B[l] * pow[len[r] - len[l]]) % MOD;
            long x = (B[r] - sub + MOD) % MOD;
            
            res[i++] = (int) ((x * (A[r] - A[l])) % MOD);
        }

        return res;
    }
    public static void main(String args[]){
            System.out.println(Arrays.toString(sumAndMultiply("9223538386222334255", new int[][]{{0,0},{0,2},{0,3},{0,4},{0,5},{0,8},{0,9},{0,10},{0,11},{0,13},{0,16},{0,18},{1,1},{1,2},{1,3},{1,5},{1,7},{1,8},{1,12},{1,14},{1,16},{1,18},{2,3},{2,6},{2,9},{2,15},{2,16},{2,18},{3,3},{3,4},{3,6},{3,7},{3,8},{3,10},{3,15},{3,17},{4,8},{4,9},{4,11},{4,14},{4,16},{5,9},{5,11},{5,12},{5,16},{5,17},{5,18},{6,8},{6,17},{6,18},{7,8},{7,9},{7,10},{7,13},{7,14},{7,15},{7,16},{8,17},{8,18},{9,13},{10,10},{10,11},{10,13},{10,14},{10,15},{10,18},{11,14},{11,15},{12,15},{12,17},{13,14},{13,16},{13,17},{14,15},{14,18},{15,18}})));
    }
}

