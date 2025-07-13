import java.util.*;
public class PascalTriangle {
        public static List<Integer> generateRow(int row){
            List<Integer> temp = new ArrayList<Integer>();
            int res = 1;
            temp.add(1);
            for(int col = 1 ; col < row ; col++){
                res = res * (row - col);
                res = res / col;
                temp.add(res);
            }
            return temp;
        }

        public static List<List<Integer>> generate(int numRows) {
            List<List<Integer>> lst = new ArrayList<List<Integer>>();

            for(int row = 1 ; row <= numRows ; row++){
                lst.add(generateRow(row));
            }

            return lst;
    }
    public static void main(String args[]){
        System.out.println(generate(5));
    }
}
