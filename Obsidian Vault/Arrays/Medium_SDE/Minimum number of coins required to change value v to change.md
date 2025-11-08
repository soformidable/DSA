problem : given value v, change it to coins in INR and return number of coins required (along with coins)

code: https://github.com/soformidable/DSA/blob/main/Arrays/Medium/MinimumCoins.java


currency = {1,2,5,10,50,100,500,1000}

Approach:

1. Create array curr
2. run a for loop starting from curr.length -1 to 0
3. run a while loop while(v>=curr\[i])
	1. This will reduce v by the value it is closest to by still smaller (for example for 70 --> coin = 50)
	2. decrement v by curr\[i]
	3. increment coins
	4. add curr\[i] to lst (optional)
4. return coins



Code:
```
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
```