import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardPathMaxSum{

public static int[] pathsWithMaxScore_OP(List<String> board) {
    int n = board.size();
    int MOD = 1_000_000_007;

    int[][] score = new int[n][n];
    int[][] ways = new int[n][n];

    // -1 means unreachable
    for (int i = 0; i < n; i++) {
        Arrays.fill(score[i], -1);
    }

    score[n - 1][n - 1] = 0;
    ways[n - 1][n - 1] = 1;

    for (int i = n - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {

            char c = board.get(i).charAt(j);

            if (c == 'X' || c == 'S')
                continue;

            int best = -1;
            int count = 0;

            int[][] next = {
                {i + 1, j},
                {i, j + 1},
                {i + 1, j + 1}
            };

            for (int[] p : next) {
                int r = p[0], col = p[1];

                if (r >= n || col >= n)
                    continue;

                if (score[r][col] == -1)
                    continue;

                if (score[r][col] > best) {
                    best = score[r][col];
                    count = ways[r][col];
                } else if (score[r][col] == best) {
                    count = (count + ways[r][col]) % MOD;
                }
            }

            if (best == -1)
                continue;

            int value = (c == 'E') ? 0 : c - '0';

            score[i][j] = best + value;
            ways[i][j] = count;
        }
    }

    if (ways[0][0] == 0)
        return new int[]{0, 0};

    return new int[]{score[0][0], ways[0][0]};
}



    public static int[] pathsWithmaxscore(List<String> board) {

        Map<Long,Long> res = new HashMap<Long,Long>();

        move(board,res,0,0,0);

        long key = 0;
        int count = 0;

        if(!res.isEmpty()){
            key = Collections.max(res.entrySet(), Map.Entry.comparingByKey()).getKey();
            count = (int)((res.get(key)) % ((Math.pow(10,9)) + 7));
        }
        else{
            key = 0;
            count = 0;
        }

        return new int[]{(int)key,(int)count};
    }

    public static void move(List<String> board, Map<Long,Long> res, long sum, int i, int j){
        if(i>=board.size() || j>=board.get(i).length() || board.get(i).charAt(j) == 'X'){
            return;
        }
        else if(board.get(i).charAt(j) == 'S'){
            res.put(sum, 1L + res.getOrDefault(sum,0L));
            return;
        }
        else
            if(board.get(i).charAt(j) == 'E')
                sum+=0;
            else
                sum += Integer.parseInt(String.valueOf(board.get(i).charAt(j)));
        
            move(board,res,sum,i+1,j);
            move(board,res,sum,i+1,j+1);
            move(board,res,sum,i,j+1);
    }
    
    public static void main(String args[]){
        System.out.println(Arrays.toString(pathsWithMaxScore_OP(Arrays.asList(new String[]{"E12","1X1","21S"}))));
    }
}