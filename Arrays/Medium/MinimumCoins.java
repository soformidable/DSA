import java.util.*;

public class MinimumCoins {
    public static int getCoins(int v){
        int coins = 0;
        int curr[] = {1,2,5,10,50,100,500,1000};
        List<Integer> lst = new ArrayList<Integer>();

            for(int i = curr.length-1 ; i >=0 ; i--){
                while(v>=curr[i]){
                    v -= curr[i];
                    coins++;
                    lst.add(curr[i]);
                }
            }
        System.out.println(lst);
        return coins;
    }
    public static void main(String args[]){
        
        System.out.println(getCoins(55));
    }
}
