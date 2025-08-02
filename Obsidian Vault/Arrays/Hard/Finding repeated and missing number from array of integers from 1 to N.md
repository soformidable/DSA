https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/


Given an array of integers from 1 to N ->
{3,2,1,3,5}
3 -> repeated
4 -> missing

Approach 1:

1. Create an array of Integer with size nums.length+1
2. Arrays.fill(arr,0);
3. Create a List\<Integer>
4. lst.add(0,0); lst.add(1,0);
5. Loop from 0 to n-1 on nums and add 1 to every occurrence of the number nums\[i] in arr\[i]. Then retrieve then values from arr\[i] where it is >1 and == 0 
```
        for(int i = 0 ; i < nums.length ; i ++)

            arr[nums[i]] += 1;


		lst.add(0, 0);

        lst.add(1, 0);

        for(int i = 0 ; i < arr.length -1 ; i++){

            if(arr[i+1] == 0)

                lst.set(0,i+1);

            if(arr[i+1] > 1)

                lst.set(1 , nums[i]);

        }

		
```


**Approach 2:**

X -> Repeating number
Y -> Missing number

Using equations and the formula
(X^2 - Y^2 ) = (X + Y)(X - Y)

S -> Sum of all numbers from 1 to N
Sn -> Sum of all elements in num

S2 -> Sum of squares of all numbers from 1 to N
S2n -> Sum of squares of all elements in num.

Now, 
(X^2 - Y^2 ) => S2 - S2n
(X - Y) => S - Sn
Then ->
(X + Y) = (X^2 - Y^2 ) / (X - Y)
**Find X + Y**

To Find X -> 
X = ((X + Y) + (X - Y))/2
Y = X-(X-Y)

Code:

```
public static int[] findMissingNumber(int nums[]){

        int n = nums.length;

  

        long s = 0 , sn = 0 , s2 = 0 , s2n = 0;

        for(int i = 0 ; i < n ; i++){

            s+=nums[i];

            s2+=nums[i]*nums[i];

        }

        sn = (n * (n+1))/2;

        s2n = (n * (n + 1) * ((2 * n) + 1))/6;

  

        // X - Y

        long val1 = s - sn;

  

        //X^2 - Y^2

        long val2 = s2 - s2n;

  

        // (X+Y) = (X^2 - X^2)/(X - Y)

        val2 = val2 / val1;

  
  

        //Find X and Y: X = ((X+Y)+(X-Y))/2 and Y = X-(X-Y),

        // Here, X-Y = val1 and X+Y = val2:

        long x = (val1 + val2)/2;

        long y = x - val1;

  
  

        return new int[]{(int)x,(int)y};

    }
```




