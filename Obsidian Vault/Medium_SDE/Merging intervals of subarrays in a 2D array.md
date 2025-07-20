https://leetcode.com/problems/merge-intervals/description/

For example:
\[1,3],\[2,6],\[8,10],\[15,19]   ---> \[1,6],\[8,10],\[15,19]

*Note* -> The subarrays can be in a shuffled order


Steps:

1. Sort the 2D array based on arr\[i]\[0] ---> [[Sorting a 2D array based on first element of each subarray]]
2. Start first loop where start = arr\[i]\[0] and end = arr\[i]\[1]
3. Create a list of list of integers lst.
4. We need to skip the intervals that are possible to merge in the current interval and added to the lst
```
		->             if(!lst.isEmpty() && end <= lst.get(lst.size() - 1).get(1))
				               continue;
```
5. Start another loop j from i  + 1 to n and check ->
```
		        if(intervals[j][0] <= end)

                    end = Math.max(end , intervals[j][1]);

		              else break;
```
6. Finally lst.add(Arrays.asList(start,end));
7. Return the lst as int\[]\[]  by using comparator : [[Converting a list of list of integers to a 2D array]]



Code:

```
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
```