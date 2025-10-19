import java.util.*;

public class MinimumPlatforms {
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
        return  platforms;
    }
    public static int[] returnIntegerTimes(String arr[]){
        List<Integer> lst = new ArrayList<Integer>();
        for (String str : arr)
            lst.add(Integer.parseInt(str.replace(":", "")));
        
        return lst.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String args[]){
        String arrival[] = {"9:00", "9:45", "9:55", "11:00", "15:00", "18:00"};
        String departure[] = {"9:20", "12:00", "11:30", "11:50", "19:00", "20:00"};

        //TEST
        String str = "23:00";
        int num = Integer.parseInt(str.replace(":", ""));

        System.out.println(getPlatforms(arrival, departure));
    }
}
