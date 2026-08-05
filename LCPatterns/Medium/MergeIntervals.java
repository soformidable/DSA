 import java.util.*;
 
 public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();
        
        int current[] = intervals[0];
        result.add(current);

        for(int[] interval : intervals){
            if(current[1] >= interval[0])
                current[1] = Math.max(interval[1],current[1]);
            else{
                current = interval;
                result.add(current);
                }
            
        }
        return result.toArray(new int[result.size()][]);
    }
    public static void main(String[] args) {
        int result[][] = merge(new int[][]{{1,3},{2,6},{15,18},{13,17}});
        for(int res[] : result){
            System.out.print(Arrays.toString(res)+"\t");
        }
    }
}
