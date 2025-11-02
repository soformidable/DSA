
Problem: Given value\[] , weight\[], N , W, find the maximum profit.

Code: https://github.com/soformidable/DSA/blob/main/Arrays/Medium/FractionalKnapsack.java

### Approach:

1. Add value array, weight array to List<int\[]>
2. Sort the lst by decreasing order of value/weight
	1. lst.sort(Comparator.comparingDouble((int\[] a) -> -((double) a\[0]/a\[1])));
3. Iterate the list
	1. if current_weight >= x\[1]
		1. add total profit
		2. subtract current_weight
	2. else
		1. use ratio proportions using cross multiplication (double casted) to add to total_profit
		2. total_profit += ((double)x\[0]*(double)remaining_weight)/(double)x\[1];
		3. break; (break out of the loop to avoid total_profit additions)
4. return double total_profit

Code:

```
    public static double getProfit(int values[],int weight[], int N, int W){

  

        List<int[]> lst = new ArrayList<int[]>();

        int remaining_weight = W;

        double total_profit = 0;

  

        for(int i = 0 ; i < values.length ; i++){

            lst.add(new int[]{values[i],weight[i],i});

        }

  

        lst.sort(Comparator.comparingDouble((int[] a) -> -((double) a[0]/a[1])));

  

        for(int[] x : lst){

            System.out.println(x[1]);

        }

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
```