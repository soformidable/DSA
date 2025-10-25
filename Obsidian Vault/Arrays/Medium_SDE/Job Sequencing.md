
problem: Given a two dimensional array with (job_id, deadline, profit) and N (number of jobs); Find the total number of scheduled jobs and the maximized profit

code:



Approach:
1. Sort the List<int\[]> by the profit in DESC
2. Find the maximum Deadline from the list
3. Create Array slot with size maximum Deadline + 1 (1-indexed)
4. Fill -1 in slot
5. Run a for each loop jobs\[] : job list
	1. run a for loop with j = deadline (job\[1])
	2. j>0 ; j-- ---> This is crucial to check if any other slots before the current deadline are available
	3. check if slot\[j] == -1 (free)
		1. schedule the job --> slot\[j] == job\[1] (using ID)
		2. total_jobs++
		3. total_profit += job\[2];
6. return new int\[]{total_jobs,total_profit}


Code:
```
    public static int[] jobs(int jobs[][], int N){

  

        if(jobs.length == 0 || jobs == null || N ==0)

            return new int[]{0,0};

  

        List<int[]> job_list = new ArrayList<int[]>();

        int total_profit = 0, total_jobs = 0;

  

        for(int job[]: jobs)

            job_list.add(job);

  

        job_list.sort(Comparator.comparingInt(a -> -a[2]));

  

        int maxDeadline = 0;

        for(int[] job: job_list)

            maxDeadline = Math.max(maxDeadline,job[1]);

        int slot[] = new int[maxDeadline+1];

        Arrays.fill(slot,-1);

  

        for(int job[] : job_list){

            // The second for loop with j > 0 and j-- is to check if any other slot other than current deadline is available that lies between 0 and currentdeadline

            // The else condition is to check if any previous empty slot is available

            for(int j = job[1]; j > 0 ; j--){

                if(slot[j] == -1){

                    slot[j] = job[0];

                    total_profit += job[2];

                    total_jobs++;

                    break;

                }

            }

        }

  

        return new int[]{total_jobs, total_profit};

    }
```
