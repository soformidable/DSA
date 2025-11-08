import java.util.*;

public class AssignCookies {
    public static int assign(int cookie[], int greed[]){
        if(greed.length == 0 || cookie.length == 0 || greed == null || cookie == null)
            return 0;
        int happy = 0;
        int j = cookie.length-1;

        Arrays.sort(cookie);
        Arrays.sort(greed);

        for(int i = greed.length-1 ; i>=0 ; i--){
            if(j >= 0 && cookie[j]>=greed[i]){
                happy++;
                j--;
            }
        }
        return happy;
    }
    public static void main(String args[]){
        System.out.println(assign(new int[]{1,1}, new int[]{1,2,3}));
    }
}
