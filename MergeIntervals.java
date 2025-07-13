import java.util.*;

class MergeIntervals{
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals , Comparator.comparingInt(a -> a[0]));
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        for(int i = 0 ; i < intervals.length ; i++){
            int start = intervals[i][0];
            int end = intervals[i][1];

            if(!lst.isEmpty() && end <= lst.get(lst.size() - 1).get(1))
                continue;

            for(int j = i + 1 ; j < intervals.length ; j ++){
                if(intervals[j][0] <= end)
                    end = Math.max(end , intervals[j][1]);
                else break;
            }
            lst.add(Arrays.asList(start,end));
        }
            return lst.stream()
              .map(innerList -> innerList.stream()
                                        .mapToInt(Integer::intValue)
                                        .toArray())
              .toArray(int[][]::new);
    }

    public static void main(String args[]){
        System.out.println(merge(new int[][]{{1,3},{2,6},{8,10},{15,18},{17,19}}));
        System.out.println(merge(new int[][]{{1,4},{4,5}}));
        //int arr[][] = merge(new int[][]{{1,3},{2,6},{8,10},{15,18},{17,19}});
        //int arr[][] = merge(new int[][]{{1,4},{4,5}});
        // for(int i = 0 ; i < arr.length ; i ++)
        //     for(int j = 0 ; j < arr[i].length ; j++)
        //         System.out.print(arr[i][j]+ "\t");
    }
}