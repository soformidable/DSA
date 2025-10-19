
Problem: Given two String arrays arrival\[] and departure\[] --> find the minimum number of platforms required (Time is given in 24H format in String "23:00")

Code: https://github.com/soformidable/DSA/blob/main/Arrays/Medium/MinimumPlatforms.java


### Optimal approach:

1. Convert the 24H arrival and departure times into integers (9:00 --> 900)
	1. As it's a single day the max value can be 23:99
2. Sort the arrival and departure integer times separately
	1. We only need to know **how many trains are currently at the station at any given time**.
	2. Think of all arrival times as “start events,”
	3. and all departure times as “end events.”
	4. We don’t care _which_ train is which — we only care about **when a train starts occupying a platform and when one leaves**.
	5. ==index sync doesn’t matter — only the relative **timing order** does.==
	6. ![[Pasted image 20251019173605.png]]
3. use platforms = 1 and needed =1 (first train always needs 1 platform)
	1. needed tracks the overlapping trains and previous departed platform count
4. use i = 1 (arrival) and j = 0 (departure)
	1. ![[Pasted image 20251019174513.png]]
	2. initializing i = 1 as first train always arrives and required 1 platform
	3. initializing j = 0 as departure of the first train is not processed yet
5. run a loop to iterate through all the arrivals (and departures) using i and j
6. if current arrival <= current departure (arrival and departure at the same time will require new platform)
	1. needed++
	2. i++ (move the arrival forward to check the next train)
7. else
	1. needed-- (previous train has departed)
	2. j++ (move to the next departure)
8. Before ending the loop check the maximum between platforms and needed
9. return  platforms


Code:
```
public static int getPlatforms(String arrival[],String departure[]){

  

        if(arrival.length == 0 || departure.length == 0 || arrival == null || departure == null)

            return 0;

        int platforms = 1;

        int needed = 1;

        // i --> 1 because first train is already arrived and no point in comparing it.

        // j --> 0 because we still need to process the first train's departure time

        int i = 1, j = 0;

  

        int[] arr_int = returnIntegerTimes(arrival);

        int[] dep_int = returnIntegerTimes(departure);

        Arrays.sort(arr_int);

        Arrays.sort(dep_int);

  

        while(i<arrival.length && j<arrival.length){

            if(arr_int[i] <= dep_int[j]){

                needed++;

                i++;

            }

            else{

                needed--;

                j++;

            }

            platforms = Math.max(platforms,needed);

        }      

        return  platforms;

    }

    public static int[] returnIntegerTimes(String arr[]){

        List<Integer> lst = new ArrayList<Integer>();

        for (String str : arr)

            lst.add(Integer.parseInt(str.replace(":", "")));

        return lst.stream().mapToInt(Integer::intValue).toArray();

    }
```


