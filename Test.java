import java.util.*;

public class Test {
    public static void main(String args[]){
        List<List<Integer>> lst = new ArrayList<List<Integer>>();
        //lst.add(new ArrayList<Integer>());
       lst.add(Arrays.asList(new Integer[]{1,2,3,4}));
       Integer arr[] = new Integer[]{5,6,7,8,9};
       lst.add(Arrays.asList(arr));

    }
}
