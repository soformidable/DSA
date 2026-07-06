import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

public class RemoveCoveredIntervals {

    public static int removeCoveredIntervals(int[][] intervals) {

        if(intervals == null || intervals.length == 0)
            return 0;

            Arrays.sort(intervals, (a, b) -> {
                if (a[0] != b[0])
                // First sorting by ascending start time
                    return a[0] - b[0];
                // Sorting by descending end time
                return b[1] - a[1];
            });

            int count = 0;
            int maxEnd = 0;

            for (int[] interval : intervals) {
                if (interval[1] > maxEnd) {
                    count++;
                    maxEnd = interval[1];
                }
            }
        return count;       
    }
    public static void main(String args[]){

        int arr[][] = new int[][]{{1,4},{3,6},{2,8}};
        System.out.println(removeCoveredIntervals(arr));
    }

}
