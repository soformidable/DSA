
https://leetcode.com/problems/sort-colors/description/

Sort the array containing 0,1,2 with O(n) and one pass, no extra space


Approach 1

1. Count the number of 0,1,2
Use Arrays.fill(0,c0,0); (c0,len-c2,1); (len-c2,len,2)



Approach 2

- arr\[0….low-1] contains 0. \[Extreme left part]
- arr\[low….mid-1] contains 1.
- arr\[high+1….n-1] contains 2. \[Extreme right part], n = size of the array

![[Pasted image 20250620222250.png]]

![[Pasted image 20250620222343.png]]


Use low, mid, high to place 0 , 1 and 2 at correct positions.

```
        while(mid <= high){

            if(nums[mid] == 0){

                swap(nums,low++,mid++);
		// Same as low++ and mid++ after the swap
            }

            else if(nums[mid] == 1)

                mid++;

            else{

                swap(nums,mid,high--);

            }

        }

  

    }

    public static void swap(int arr[], int i , int j){

        int temp = arr[i];

        arr[i] = arr[j];

        arr[j] = temp;

    }
```