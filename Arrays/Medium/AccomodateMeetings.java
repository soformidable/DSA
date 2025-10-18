import java.util.*;


public class AccomodateMeetings {
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
    public static void main(String args[]){
        int start[] = {0,5,15};
        int end[] = {30,10,20};
        System.out.println(meetings(start, end));
    }
}
