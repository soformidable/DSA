problem: Given two arrays start and end, return a list of the ids of the maxixmum meetings that can be arranged in a day

code:


Approach:
1. End times might not be sorted, so sort the end times by keeping track of original indices if start and end
	1. use comparator for this
	2. [[Using Comparator to keep original indices after sorting or Sorting multiple arrays at once in Sync]]
	3. Sort using end
2. Run a loop for the meetings list of arrays
3. use prevMeetingEnd = 0 to track the last meeting end time
4. if start > prevMeetingEnd then add the index to result list
5. return result list

Code:
```
    public static List<Integer> meetings(int start[],int end[]){

  

        List<int[]> meetings = new ArrayList<>();

  

        for(int i = 0 ; i < start.length ; i ++){

            //indices are set i+1 as they are 1 indexed in the problem (1st meeting, 2nd meeting, 3rd meeting)

            meetings.add(new int[]{end[i],start[i],i+1});

        }

  

        // Sorting based on end as 0 is end in meetings

        meetings.sort(Comparator.comparingInt(a -> a[0]));

  

        List<Integer> result = new ArrayList<Integer>();

        int prevMeetingEnd = 0;

  

        for(int[] x : meetings){

            if(x[1] > prevMeetingEnd){

                result.add(x[2]);

                prevMeetingEnd = x[1];

            }

        }

        return result;

    }
```

