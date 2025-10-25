import java.util.*;

public class JobSequencing {
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
    public static void main(String args[]){
        int arr[][] = new int[][]{{1,4,20},{2,1,10},{3,1,40},{4,1,30}};
        System.out.println(Arrays.toString(jobs(arr, 4)));
    }
}
