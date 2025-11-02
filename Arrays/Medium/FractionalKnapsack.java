import java.util.*;

public class FractionalKnapsack{
    public static double getProfit(int values[],int weight[], int N, int W){

        List<int[]> lst = new ArrayList<int[]>();
        int remaining_weight = W;
        double total_profit = 0;

        for(int i = 0 ; i < values.length ; i++){
            lst.add(new int[]{values[i],weight[i],i});
        }

        lst.sort(Comparator.comparingDouble((int[] a) -> -((double) a[0]/a[1])));
        
        for(int[] x : lst){
            if(remaining_weight >= x[1]){
                total_profit+= x[0];
                remaining_weight = remaining_weight - x[1];
            }
            else{
                total_profit += ((double)x[0]*(double)remaining_weight)/(double)x[1];
                break;
            }
        }

        return total_profit;
    }
    public static void main(String args[]){
        int values[] = new int[]{100,60,120};
        int weight[] = new int[]{20,10,30};

        System.out.println(getProfit(values, weight,3,50));

    }
}